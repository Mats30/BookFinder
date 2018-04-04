package scrapper.core;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

class EbookpointScrapperTest {
    private static final String PATH = "src/test/resources/informatyka.1";
    private EbookpointScrapper scrapper = new EbookpointScrapper();

    @Test
    void scrapDefaultPageFromFile() {
        File file = new File(PATH);
        try {
            Elements bookDivs = Jsoup.parse(file, "UTF-8").getElementsByClass("classPresale");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
