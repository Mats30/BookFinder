package scrapper.mapper.type;

import model.BookType;
import org.jsoup.nodes.Element;

public interface TypeMapper {
    BookType mapType(Element element);
}
