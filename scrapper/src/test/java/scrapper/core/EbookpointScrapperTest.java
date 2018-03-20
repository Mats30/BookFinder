package scrapper.core;

import model.Book;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class EbookpointScrapperTest {
    private EbookpointScrapper scrapper = new EbookpointScrapper();

    @Test
    public void scrapEbookPointSiteAndReturnListOfBooks() {
        List<Book> listOfBooks = scrapper.scrap();
        assertThat(listOfBooks).isEqualTo(Lists.emptyList());
    }
}
