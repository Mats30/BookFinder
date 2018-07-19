package scrapper.mapper;

import model.Book;
import model.BookType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scrapper.mapper.price.PriceMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EbookpointMapperTest {
    private static final String TEST_FILE_PATH = "src/test/resources/test_page.html";
    private File testFile = new File(TEST_FILE_PATH);
    private EbookpointMapper mapper = new EbookpointMapper(new PriceMapper() {
        @Override
        public List<Double> parseBooksPrices(Elements bookContent) {
            return null;
        }

        @Override
        public double scrapPrice(Element e, String attributePriceKey) {
            return 0;
        }
    });
    private Logger LOG = LoggerFactory.getLogger(EbookpointMapperTest.class);

    @Test
    void parseEbookpointPageFromFileAndMapItToBooksList() {
        //given
        Elements bookDivs = null;
        try {
            bookDivs = Jsoup.parse(testFile, "iso-8859-2").getElementsByClass("classPresale");
        } catch (IOException e) {
            LOG.error("Error while parsing test file");
        }
        Book first = new Book.Builder()
                .withAuthor("Karol Nienartowicz")
                .withTitle("Górskie wyprawy fotograficzne")
                .withBookStore("ebookpoint")
                .withURL("//ebookpoint.pl/ksiazki/gorskie-wyprawy-fotograficzne-karol-nienartowicz,gowyfo.htm")
                .withType(BookType.MOBI_EPUB_PDF)
                .withOldPrice(Double.parseDouble("59"))
                .withNewPrice(Double.parseDouble("47.2"))
                .build();
        Book second = new Book.Builder()
                .withAuthor("Aditya Bhargava")
                .withTitle("Algorytmy. Ilustrowany przewodnik")
                .withBookStore("ebookpoint")
                .withURL("//ebookpoint.pl/ksiazki/algorytmy-ilustrowany-przewodnik-aditya-bhargava,algoip.htm")
                .withType(BookType.MOBI_EPUB_PDF)
                .withOldPrice(Double.parseDouble("54.89"))
                .withNewPrice(Double.parseDouble("43.91"))
                .build();

        Book third = new Book.Builder()
                .withAuthor("Matt Zandstra")
                .withTitle("PHP. Obiekty, wzorce, narzędzia. Wydanie V")
                .withBookStore("ebookpoint")
                .withURL("//ebookpoint.pl/ksiazki/php-obiekty-wzorce-narzedzia-wydanie-v-matt-zandstra,phpob5.htm")
                .withType(BookType.MOBI_EPUB_PDF)
                .withOldPrice(Double.parseDouble("89"))
                .withNewPrice(Double.parseDouble("71.2"))
                .build();

        Book fourth = new Book.Builder()
                .withAuthor("Adam Józefiok")
                .withTitle("Windows 7 PL. Pierwsza pomoc")
                .withBookStore("ebookpoint")
                .withURL("//ebookpoint.pl/ksiazki/windows-7-pl-pierwsza-pomoc-adam-jozefiok,win7pp.htm")
                .withType(BookType.PDF)
                .withOldPrice(Double.parseDouble("17.9"))
                .withNewPrice(Double.parseDouble("14.32"))
                .build();

        List<Book> expectedBooksList = List.of(first, second, third, fourth);
        //when
        List<Book> booksList = mapper.map(bookDivs);
        //then
        assertEquals(expectedBooksList, booksList);
    }
}
