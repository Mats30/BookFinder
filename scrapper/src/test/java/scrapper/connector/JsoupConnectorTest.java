package scrapper.connector;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class JsoupConnectorTest {
    private static final Logger LOG = LoggerFactory.getLogger(JsoupConnectorTest.class);
    private JsoupConnector jsoupConnector  = new JsoupConnector();

    @Test
    void connectToEbookpointSiteWithValidURL() {
        Optional<Document> document = jsoupConnector.connect("https://ebookpoint.pl/kategorie/informatyka");
        LOG.info("Downloaded ebookpoint site", document);
        assertThat(document).isNotEqualTo(Optional.empty());
    }

}
