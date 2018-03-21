package scrapper.mapper;

import model.Book;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EbookpointMapperTest {
    private static final String TEST_FILE_PATH = "src/test/resources/informatyka.1";
    private EbookpointMapper mapper = new EbookpointMapper();
    private File testFile = new File(TEST_FILE_PATH);

    @Test
    void parseEbookpointPageFromFileAndMapItToBooksList() {
        //given
        Elements bookDivs = null;
        try {
            bookDivs = Jsoup.parse(testFile, "iso-8859-2").getElementsByClass("classPresale");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Book first = new Book.Builder()
                .withAuthor("Karol Nienartowicz")
                .withTitle("Górskie wyprawy fotograficzne")
                .withBookStore("ebookpoint")
                .withURL("//ebookpoint.pl/ksiazki/gorskie-wyprawy-fotograficzne-karol-nienartowicz,gowyfo.htm")
                .withOldPrice(new BigDecimal(59))
                .withNewPrice(new BigDecimal(47.2))
                .build();
        Book second = new Book.Builder()
                .withAuthor("Aditya Bhargava")
                .withTitle("Algorytmy. Ilustrowany przewodnik")
                .withBookStore("ebookpoint")
                .withURL("//ebookpoint.pl/ksiazki/algorytmy-ilustrowany-przewodnik-aditya-bhargava,algoip.htm")
                .withOldPrice(new BigDecimal(54.89))
                .withNewPrice(new BigDecimal(43.91))
                .build();

        Book third = new Book.Builder()
                .withAuthor("Matt Zandstra")
                .withTitle("PHP. Obiekty, wzorce, narzędzia. Wydanie V")
                .withBookStore("ebookpoint")
                .withURL("//ebookpoint.pl/ksiazki/php-obiekty-wzorce-narzedzia-wydanie-v-matt-zandstra,phpob5.htm")
                .withOldPrice(new BigDecimal(89))
                .withNewPrice(new BigDecimal(71.2))
                .build();

        Book fourth = new Book.Builder()
                .withAuthor("Adam Józefiok")
                .withTitle("Windows 7 PL. Pierwsza pomoc")
                .withBookStore("ebookpoint")
                .withURL("//ebookpoint.pl/ksiazki/windows-7-pl-pierwsza-pomoc-adam-jozefiok,win7pp.htm")
                .withOldPrice(new BigDecimal(17.9))
                .withNewPrice(new BigDecimal(14.32))
                .build();
        List<Book> expectedBooksList = new ArrayList<>(); // TODO: switch to List.of() when JDK9 will be on board
        expectedBooksList.add(first);
        expectedBooksList.add(second);
        expectedBooksList.add(third);
        expectedBooksList.add(fourth);
        //when
        List<Book> booksList = mapper.map(bookDivs);
        //then
        assertEquals(expectedBooksList, booksList);
    }
}
