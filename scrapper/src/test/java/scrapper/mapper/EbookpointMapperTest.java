package scrapper.mapper;

import model.Book;
import model.BookType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import providers.BooksProvider;
import scrapper.mapper.price.PriceMapper;
import scrapper.mapper.type.TypeMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

class EbookpointMapperTest {
    private static final String TEST_FILE_PATH = "src/test/resources/ebookpoint_test_page.html";
    private static final String NEW_PRICE_KEY = "price";
    private static final String OLD_PRICE_KEY = "constprice";
    private File testFile = new File(TEST_FILE_PATH);
    private PriceMapper priceMapper = Mockito.mock(PriceMapper.class);
    private TypeMapper typeMapper = Mockito.mock(TypeMapper.class);
    private EbookpointMapper mapper = new EbookpointMapper(priceMapper, typeMapper);
    private Logger LOG = LoggerFactory.getLogger(EbookpointMapperTest.class);

    @Test
    void parseEbookpointPageFromFileAndMapItToBooksList() {
        //given
        Optional<Elements> bookDivs = Optional.empty();
        try {
            bookDivs = Optional.of(Jsoup.parse(testFile, "iso-8859-2").getElementsByClass("classPresale"));
        } catch (IOException e) {
            LOG.error("Error while parsing test file");
        }

        List<Book> expectedBooksList = BooksProvider.provideAllBooksFromEbookpoint();
        when(typeMapper.mapType(any(Element.class))).thenReturn(BookType.MOBI_EPUB_PDF);
        when(priceMapper.scrapPrice(any(Element.class), eq(NEW_PRICE_KEY))).thenReturn(10.00);
        when(priceMapper.scrapPrice(any(Element.class), eq(OLD_PRICE_KEY))).thenReturn(20.00);
        //when
        List<Book> booksList = bookDivs.map(elements -> mapper.map(elements)).orElse(new ArrayList<>());
        //then
        assertEquals(expectedBooksList, booksList);
    }
}
