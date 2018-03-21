package scrapper.core;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import scrapper.connector.LibConnector;

import java.io.File;
import java.util.Optional;

@Component
class EbookpointScrapper implements Scrapper {
    private LibConnector connector;

    @Autowired
    public void setConnector(LibConnector connector) {
        this.connector = connector;
    }

    public Elements scrap(String url) {
        Optional<Document> htmlPage = connector.connect(url);
        Elements elements = null;
        if (htmlPage.isPresent()) {
            elements = htmlPage.get().getElementsByClass("classPresale");
        }
        return elements;
    }
}
