package scrapper.mapper;

import model.Book;
import model.BookType;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EbookpointMapperTest {
    private static final String TEST_FILE_PATH = "src/test/resources/informatyka.html";
    private File testFile = new File(TEST_FILE_PATH);
    private EbookpointMapper mapper = new EbookpointMapper();
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
                .withOldPrice(Long.parseLong("59"))
                .withNewPrice(Long.parseLong("472"))
                .build();
        Book second = new Book.Builder()
                .withAuthor("Aditya Bhargava")
                .withTitle("Algorytmy. Ilustrowany przewodnik")
                .withBookStore("ebookpoint")
                .withURL("//ebookpoint.pl/ksiazki/algorytmy-ilustrowany-przewodnik-aditya-bhargava,algoip.htm")
                .withType(BookType.MOBI_EPUB_PDF)
                .withOldPrice(Long.parseLong("5489"))
                .withNewPrice(Long.parseLong("4391"))
                .build();

        Book third = new Book.Builder()
                .withAuthor("Matt Zandstra")
                .withTitle("PHP. Obiekty, wzorce, narzędzia. Wydanie V")
                .withBookStore("ebookpoint")
                .withURL("//ebookpoint.pl/ksiazki/php-obiekty-wzorce-narzedzia-wydanie-v-matt-zandstra,phpob5.htm")
                .withType(BookType.MOBI_EPUB_PDF)
                .withOldPrice(Long.parseLong("89"))
                .withNewPrice(Long.parseLong("712"))
                .build();

        Book fourth = new Book.Builder()
                .withAuthor("Adam Józefiok")
                .withTitle("Windows 7 PL. Pierwsza pomoc")
                .withBookStore("ebookpoint")
                .withURL("//ebookpoint.pl/ksiazki/windows-7-pl-pierwsza-pomoc-adam-jozefiok,win7pp.htm")
                .withType(BookType.PDF)
                .withOldPrice(Long.parseLong("179"))
                .withNewPrice(Long.parseLong("1432"))
                .build();

        List<Book> expectedBooksList = List.of(first, second, third, fourth);
        //when
        List<Book> booksList = mapper.map(bookDivs);
        //then
        assertEquals(expectedBooksList, booksList);
    }
}
