package scrapper;

import model.Book;

import java.io.IOException;
import java.util.List;

public interface Scrapper {
    List<Book> scrap() throws IOException;
}
