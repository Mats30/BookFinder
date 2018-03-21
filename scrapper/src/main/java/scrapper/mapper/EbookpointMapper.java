package scrapper.mapper;

import model.Book;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

class EbookpointMapper implements Mapper {
    private static final Logger LOG = LoggerFactory.getLogger(EbookpointMapper.class);

    @Override
    public List<Book> map(final Elements parsedDivs) {
        return parsedDivs
                .parallelStream()
                .filter(e -> !e.select(".changeFormat2").attr("oszczedzasz").equals("0"))
                .map(e -> new Book.Builder()
                        .withAuthor(e.getElementsByClass("author").first().child(0).text())
                        .withTitle(e.select("h3").first().child(0).text())
                        .withBookStore("ebookpoint")
                        .withURL(e.select("h3").first().child(0).attr("href"))
                        .build())
                .collect(Collectors.toList());
    }
}
