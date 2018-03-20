package scrapper.core;

import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import scrapper.Scrapper;
import scrapper.connector.LibConnector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
class EbookpointScrapper implements Scrapper {
    private static String EBOOK_BASE_URL = "https://ebookpoint.pl/kategorie/informatyka";

    private LibConnector connector;

    @Autowired
    public void setConnector(LibConnector connector) {
        this.connector = connector;
    }

    public List<Book> scrap() {
        connector.connect(EBOOK_BASE_URL);
        return new ArrayList<>();
    }
}
