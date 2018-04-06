package scrapper.connector;

import org.jsoup.Connection;
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
            Connection connection = Jsoup.connect(url);
            connection.userAgent("Mozilla/5.0");
            return Optional.of(connection.get());
        } catch (IOException exception) {
            LOG.error(exception.getMessage());
        }
        return Optional.empty();
    }
}
