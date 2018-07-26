package scrapper.mapper;

import model.Book;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import scrapper.mapper.price.PriceMapper;
import scrapper.mapper.type.TypeMapper;

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
    private TypeMapper typeMapper;

    @Autowired
    public EbookpointMapper(PriceMapper ebookpointPriceMapper, TypeMapper ebookpointTypeMapper) {
        this.priceMapper = ebookpointPriceMapper;
        this.typeMapper = ebookpointTypeMapper;
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
                        .withType(typeMapper.mapType(e))
                        .withNewPrice(priceMapper.scrapPrice(e, NEW_PRICE_KEY))
                        .withOldPrice(priceMapper.scrapPrice(e, OLD_PRICE_KEY))
                        .build())
                .collect(Collectors.toList());
    }
}
