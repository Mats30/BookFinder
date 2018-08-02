package scrapper.core;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scrapper.connector.LibConnector;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EbookpointScrapperTest {
    private final Logger LOG = LoggerFactory.getLogger(EbookpointScrapperTest.class);
    private static final String PATH = "src/test/resources/ebookpoint_test_page.html";
    private LibConnector connector = mock(LibConnector.class);
    private EbookpointScrapper scrapper = new EbookpointScrapper(connector);

    @Test
    void scrapDefaultPageFromFile() {
        File testFile = new File(PATH);
        try {
            when(connector.connect(PATH)).thenReturn(Optional.of(Jsoup.parse(testFile, "UTF-8")));
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        Elements bookDivs = scrapper.scrap(PATH);
        assertThat(bookDivs.size()).isEqualTo(5);
    }

    @Test
    void scrapMalformedPage_ExpectEmptyListOfElements() {
        when(connector.connect(PATH)).thenReturn(Optional.empty());
        Elements expectedBookDivs = scrapper.scrap(PATH);
        assertThat(expectedBookDivs).isEqualTo(new Elements().empty());
    }
}
