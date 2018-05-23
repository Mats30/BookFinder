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


    @Override
    public List<Book> map(final Elements parsedDivs) {
        return parsedDivs
                .stream()
                .filter(e -> !e.select(".changeFormat2").attr("oszczedzasz").equals("0")
                        && !e.select(".changeFormat2").hasClass("type-audiobook")
                        && !e.getElementsByClass("author").text().isEmpty())
                .map(e -> new Book.Builder()
                        .withAuthor(e.getElementsByClass("author").first().child(0).text())
                        .withTitle(e.select("h3").first().child(0).text())
                        .withBookStore("ebookpoint")
                        .withURL(e.select("h3").first().child(0).attr("href"))
                        .withType(mapType(e))
//                        .withNewPrice(getNewPrice(e))
//                        .withOldPrice(getOldPrice(e))
                        .build())
                .collect(Collectors.toList());
    }

    private BookType mapType(Element element) {
        Elements bookContent = element.select(".changeFormat2");
        List<Long> prices = getBooksPrices(bookContent);
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
        else if (books.select("span").first().text().equals("PDF")) return BookType.PDF;
        return BookType.MOBI;
    }

    private boolean isPaperType(Elements books) {
        return books.select("span").get(0).text().equals("Druk");
    }

    private List<Long> getBooksPrices(Elements bookContent) {
        return bookContent.stream()
                .map(e -> Long.parseLong(e.attr("oszczedzasz")
                        .replace(".", "")))
                .collect(Collectors.toList());
    }

    private long getNewPrice(Element e) {
        Elements bookContent = e.select(".changeFormat2");
        long ebookPromo = Long.parseLong(bookContent.first().attr("oszczedzasz").replace(".", ""));
        long paperPromo = Long.parseLong(bookContent.get(1).attr("oszczedzasz").replace(".", ""));

        if (ebookPromo > paperPromo) {
            return Long.parseLong(bookContent.first().attr("price").replace(".", ""));
        } else {
            return Long.parseLong(bookContent.get(1).attr("price").replace(".", ""));
        }
    }

    private long getOldPrice(Element e) {
        Elements bookContent = e.select(".changeFormat2");
        long ebookPromo = Long.parseLong(bookContent.first().attr("oszczedzasz").replace(".", ""));
        long paperPromo = Long.parseLong(bookContent.get(1).attr("oszczedzasz").replace(".", ""));

        if (ebookPromo > paperPromo) {
            return Long.parseLong(bookContent.first().attr("constprice").replace(".", ""));
        } else {
            return Long.parseLong(bookContent.get(1).attr("constprice").replace(".", ""));
        }
    }
}
