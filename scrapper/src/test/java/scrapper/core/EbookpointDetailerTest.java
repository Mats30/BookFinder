package scrapper.core;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EbookpointDetailerTest {
    private static final String PATH = "src/test/resources/test_page.html";
    private EbookpointDetailer detailer = new EbookpointDetailer();

    @Test
    void fetchPagesNumberFromFileWithSourceCodeOfEbookpointSite() throws IOException {
        File file = new File(PATH);
        int pages = detailer.scrapPagesNumber(Jsoup.parse(file, "UTF-8"));
        assertThat(pages).isEqualTo(27);
    }

    @Test
    void fetchPagesNumberFromEmptyDocument_ExpectNoSuchElementException() {
        String baseUri = "test";
        Executable codeUnderTest = () -> detailer.scrapPagesNumber(new Document(baseUri));
        assertThrows(NoSuchElementException.class, codeUnderTest);
    }
}
