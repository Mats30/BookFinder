package scrapper.connector;

import org.jsoup.nodes.Document;

import java.util.Optional;

public interface LibConnector {
    Optional<Document> connect(String url);
}
