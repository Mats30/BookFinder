package scrapper.mapper;

import model.Book;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EbookpointMapperTest {
    private static final String PATH = "src/test/resources/informatyka.html";
    private EbookpointMapper mapper = new EbookpointMapper();

    @Test
    void parseEbookpointPageFromFileAndMapItToBooksList() {
        File file = new File(PATH);
        Elements bookDivs = null;
        try {
            bookDivs = Jsoup.parse(file, "UTF-8").getElementsByClass("classPresale");
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Book> booksList = mapper.map(bookDivs);
        assertThat(booksList.size()).isEqualTo(4);
    }
}
