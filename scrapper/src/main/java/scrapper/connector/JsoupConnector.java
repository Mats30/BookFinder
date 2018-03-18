package scrapper.connector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


class JsoupConnector implements LibConnector {
    private static final Logger LOG = LoggerFactory.getLogger(JsoupConnector.class);

    public Document connect(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException exception) {
            LOG.error(exception.getMessage());
        }
        return null;
    }
}
