package scrapper.mapper;

import model.Book;
import org.jsoup.select.Elements;

import java.util.List;

public interface Mapper {
    List<Book> map(Elements parsedDivs);
}
