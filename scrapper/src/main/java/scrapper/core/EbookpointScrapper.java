package scrapper.core;

import model.Book;
import scrapper.Scrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class EbookpointScrapper implements Scrapper {

    private static String EBOOK_BASE_URL = "https://ebookpoint.pl/kategorie/informatyka";

    public List<Book> scrap() throws IOException {
        return new ArrayList<Book>();
    }
}
