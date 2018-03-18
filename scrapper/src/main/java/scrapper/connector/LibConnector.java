package scrapper.connector;

import org.jsoup.nodes.Document;

public interface LibConnector {
    Document connect(String url);
}
