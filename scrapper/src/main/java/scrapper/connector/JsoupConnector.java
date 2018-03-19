package scrapper.connector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
class JsoupConnector implements LibConnector {
    private static final Logger LOG = LoggerFactory.getLogger(JsoupConnector.class);

    public Optional<Document> connect(String url) {
        try {
            return Optional.of(Jsoup.connect(url).get());
        } catch (IOException exception) {
            LOG.error(exception.getMessage());
        }
        return Optional.empty();
    }
}
