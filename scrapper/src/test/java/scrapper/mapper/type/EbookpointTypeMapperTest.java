package scrapper.mapper.type;

import model.BookType;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class EbookpointTypeMapperTest {
    private static final Logger LOG = LoggerFactory.getLogger(EbookpointTypeMapper.class);
    private static final String PARSED_BOOKS_FILE_PATH = "src/test/resources/parsed_books.html";
    private static final String PRESALE_CSS_CLASS = ".classPresale";
    private EbookpointTypeMapper ebookpointTypeMapper = new EbookpointTypeMapper();
    private File parsedDivsFile = new File(PARSED_BOOKS_FILE_PATH);

    @Test
    void giveArrayOfElements_ExpectTheirBookType() {
        Optional<Elements> parsedDivs = Optional.empty();
        try {
            parsedDivs = Optional.of(Jsoup.parse(parsedDivsFile, "UTF-8").select(PRESALE_CSS_CLASS));
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        List<BookType> booksTypes = parsedDivs.stream()
                .flatMap(Collection::stream)
                .map(e -> ebookpointTypeMapper.mapType(e))
                .collect(Collectors.toList());
        List<BookType> expectedBooksList = List.of(BookType.MOBI_EPUB_PDF, BookType.MOBI_EPUB_PDF, BookType.PAPER, BookType.MOBI_EPUB_PDF, BookType.PDF);
        assertThat(booksTypes).isEqualTo(expectedBooksList);
    }

    @Test
    void giveEmptyArrayOfElements_ExpectEmptyArray() {
        Elements parsedDivs = new Elements().empty();
        List<BookType> booksTypes = parsedDivs.stream()
                .map(e -> ebookpointTypeMapper.mapType(e))
                .collect(Collectors.toList());
        assertThat(booksTypes.size()).isEqualTo(0);
    }

}
