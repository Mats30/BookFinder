package scrapper.connector;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

class JsoupConnectorTest {
    private static final Logger LOG = LoggerFactory.getLogger(JsoupConnectorTest.class);
    private LibConnector jsoupConnector  = new JsoupConnector();

    @Test
    void connectToEbookpointSiteWithValidURL() {
        Optional<Document> document = jsoupConnector.connect("https://ebookpoint.pl/kategorie/informatyka");
        LOG.info("Downloaded ebookpoint site", document);
        assertThat(document).isNotEqualTo(Optional.empty());
    }

}
