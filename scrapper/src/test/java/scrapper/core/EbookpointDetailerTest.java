package scrapper.core;

import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class EbookpointDetailerTest {
    private static final String DESTINATION = "src/test/resources/informatyka.html";
    private EbookpointDetailer detailer = new EbookpointDetailer();

    @Test
    void fetchPagesNumberFromFileWithSourceCodeOfEbookpointSite() throws IOException {
        File file = new File(DESTINATION);
        int pages = detailer.scrapPagesNumber(Jsoup.parse(file, "UTF-8"));
        assertThat(pages).isEqualTo(27);
    }
}
