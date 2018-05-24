package scrapper.mapper;

import model.Book;
import model.BookType;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
class EbookpointMapper implements Mapper {
    private static final Logger LOG = LoggerFactory.getLogger(EbookpointMapper.class);
    private static final String NEW_PRICE_KEY = "price";
    private static final String OLD_PRICE_KEY = "constprice";
    private static final String BOOK_CLASS = ".changeFormat2";

    @Override
    public List<Book> map(final Elements parsedDivs) {
        return parsedDivs
                .stream()
                .filter(e -> !e.select(BOOK_CLASS).attr("oszczedzasz").equals("0")
                        && !e.select(BOOK_CLASS).hasClass("type-audiobook")
                        && !e.select(BOOK_CLASS).hasClass("type-online")
                        && !e.getElementsByClass("author").text().isEmpty())
                .map(e -> new Book.Builder()
                        .withAuthor(e.getElementsByClass("author").first().child(0).text())
                        .withTitle(e.select("h3").first().child(0).text())
                        .withBookStore("ebookpoint")
                        .withURL(e.select("h3").first().child(0).attr("href"))
                        .withType(mapType(e))
                        .withNewPrice(getPrice(e, NEW_PRICE_KEY))
                        .withOldPrice(getPrice(e, OLD_PRICE_KEY))
                        .build())
                .collect(Collectors.toList());
    }

    private BookType mapType(Element element) {
        Elements bookContent = element.select(BOOK_CLASS);
        List<Long> prices = parseBooksPrices(bookContent);
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

    private List<Long> parseBooksPrices(Elements bookContent) {
        return bookContent.stream()
                .map(e -> Long.parseLong(e.attr("oszczedzasz")
                        .replace(".", "")))
                .collect(Collectors.toList());
    }

    private long getPrice(Element e, String attributePriceKey) {
        Elements bookContent = e.select(BOOK_CLASS);
        List<Long> prices = parseBooksPrices(bookContent);
        Long price;
        if (prices.size() > 1 && (prices.get(0) > prices.get(1))) {
            price = Long.parseLong(bookContent.first().attr(attributePriceKey).replace(".", ""));
        } else if (prices.size() > 1 && (prices.get(0) < prices.get(1))){
            price = Long.parseLong(bookContent.get(1).attr(attributePriceKey).replace(".", ""));
        } else {
            price = prices.get(0);
        }
        return price;
    }
}
