package scrapper.mapper;

import model.Book;
import model.BookType;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import scrapper.mapper.price.PriceMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
final class EbookpointMapper implements Mapper {
    private static final Logger LOG = LoggerFactory.getLogger(EbookpointMapper.class);
    private static final String STORE_NAME = "ebookpoint";
    private static final String NEW_PRICE_KEY = "price";
    private static final String OLD_PRICE_KEY = "constprice";
    private static final String BOOK_CSS_CLASS = ".changeFormat2";
    private static final String AUDIOBOOK_CSS_CLASS = "type-audiobook";
    private static final String VIDEOCOURSE_CSS_CLASS = "type-online";
    private static final String DISCOUNT_ATTRIBUTE = "oszczedzasz";

    private PriceMapper priceMapper;

    @Autowired
    public EbookpointMapper(PriceMapper ebookpointPriceMapper) {
        this.priceMapper = ebookpointPriceMapper;
    }


    @Override
    public List<Book> map(final Elements parsedDivs) {
        return parsedDivs
                .stream()
                .filter(e -> !e.select(BOOK_CSS_CLASS).attr(DISCOUNT_ATTRIBUTE).equals("0")
                        && !e.select(BOOK_CSS_CLASS).hasClass(AUDIOBOOK_CSS_CLASS)
                        && !e.select(BOOK_CSS_CLASS).hasClass(VIDEOCOURSE_CSS_CLASS)
                        && !e.getElementsByClass("author").text().isEmpty())
                .map(e -> new Book.Builder()
                        .withAuthor(e.getElementsByClass("author").first().child(0).text())
                        .withTitle(e.select("h3").first().child(0).text())
                        .withBookStore(STORE_NAME)
                        .withURL(e.select("h3").first().child(0).attr("href"))
                        .withType(mapType(e))
                        .withNewPrice(priceMapper.scrapPrice(e, NEW_PRICE_KEY))
                        .withOldPrice(priceMapper.scrapPrice(e, OLD_PRICE_KEY))
                        .build())
                .collect(Collectors.toList());
    }

    private BookType mapType(Element element) {
        Elements bookContent = element.select(BOOK_CSS_CLASS);
        List<Double> prices = priceMapper.parseBooksPrices(bookContent);
        BookType type = BookType.PAPER;
        if (prices.size() > 1 && (prices.get(0) > prices.get(1))) {
            type = getEbookPacketType(bookContent);
        } else if (!isPaperType(bookContent)) {
            type = getEbookSingleType(bookContent);
        }
        return type;
    }

    private BookType getEbookPacketType(Elements books) {
        if (books.select("span").get(1).text().equals("PDF + Epub + Mobi")) return BookType.MOBI_EPUB_PDF;
        else if (books.select("span").get(1).text().equals("PDF")) return BookType.PDF;
        return BookType.MOBI;
    }

    private BookType getEbookSingleType(Elements books) {
        if (books.select("span").first().text().equals("PDF + Epub + Mobi")) return BookType.MOBI_EPUB_PDF;
        else if (books.select("span").first().text().equals("Epub + Mobi")) return BookType.EPUB_MOBI;
        else if (books.select("span").first().text().equals("PDF")) return BookType.PDF;
        return BookType.MOBI;
    }

    private boolean isPaperType(Elements books) {
        return books.select("span").get(0).text().equals("Druk");
    }

}
