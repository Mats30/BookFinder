package scrapper.mapper.type;

import model.BookType;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
final class EbookpointTypeMapper implements TypeMapper {
    private static final String BOOK_CSS_CLASS = ".changeFormat2";
    private Map<String, BookType> booksTypesMap = new HashMap<>();

    EbookpointTypeMapper() {
        booksTypesMap.put("PDF + Epub + Mobi", BookType.MOBI_EPUB_PDF);
        booksTypesMap.put("Epub + Mobi", BookType.EPUB_MOBI);
        booksTypesMap.put("Mobi", BookType.MOBI);
        booksTypesMap.put("PDF", BookType.PDF);
        booksTypesMap.put("Druk", BookType.PAPER);
    }

    @Override
    public BookType mapType(Element element) {
        Elements bookContent = element.select(BOOK_CSS_CLASS);
        return getEbookType(bookContent);
    }

    private BookType getEbookType(Elements books) {
        return booksTypesMap.getOrDefault(books.select("span").get(0).text(), BookType.PAPER);
    }
}
