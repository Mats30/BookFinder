package scrapper.mapper;

import model.Book;
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
                        .withNewPrice(Long.parseLong(e.select(".changeFormat2").first().attr("price").replace(".","")))
                        .withOldPrice(Long.parseLong(e.select(".changeFormat2").first().attr("constprice").replace(".","")))
                        .build())
                .collect(Collectors.toList());
    }
}
