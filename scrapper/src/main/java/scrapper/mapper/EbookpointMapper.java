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
                .filter(e -> !e.select(".changeFormat2").attr("oszczedzasz").equals("0"))
                .map(e -> new Book.Builder()
                        .withAuthor(e.getElementsByClass("author").first().child(0).text())
                        .withTitle(e.select("h3").first().child(0).text())
                        .withBookStore("ebookpoint")
                        .withURL(e.select("h3").first().child(0).attr("href"))
                        .withType(mapType(e))
                        .withNewPrice(getNewPrice(e))
                        .withOldPrice(getOldPrice(e))
                        .build())
                .collect(Collectors.toList());
    }

    private BookType mapType(Element e) {
        Elements bookContent = e.select(".changeFormat2");
        long ebookPromo = Long.parseLong(bookContent.first().attr("oszczedzasz").replace(".",""));
        long paperPromo = Long.parseLong(bookContent.get(1).attr("oszczedzasz").replace(".",""));

        if (ebookPromo > paperPromo) {
             if (bookContent.select("span").get(1).text().equals("PDF + Epub + Mobi")) return BookType.MOBI_EPUB_PDF;
             else if (bookContent.select("span").get(1).text().equals("PDF")) return BookType.PDF;
             else return BookType.MOBI;
        } else return BookType.PAPER;
    }

    private long getNewPrice(Element e) {
        Elements bookContent = e.select(".changeFormat2");
        long ebookPromo = Long.parseLong(bookContent.first().attr("oszczedzasz").replace(".",""));
        long paperPromo = Long.parseLong(bookContent.get(1).attr("oszczedzasz").replace(".",""));

        if (ebookPromo > paperPromo) {
            return Long.parseLong(bookContent.first().attr("price").replace(".",""));
        } else {
            return Long.parseLong(bookContent.get(1).attr("price").replace(".",""));
        }
    }

    private long getOldPrice(Element e) {
        Elements bookContent = e.select(".changeFormat2");
        long ebookPromo = Long.parseLong(bookContent.first().attr("oszczedzasz").replace(".",""));
        long paperPromo = Long.parseLong(bookContent.get(1).attr("oszczedzasz").replace(".",""));

        if (ebookPromo > paperPromo) {
            return Long.parseLong(bookContent.first().attr("constprice").replace(".",""));
        } else {
            return Long.parseLong(bookContent.get(1).attr("constprice").replace(".",""));
        }
    }
}
