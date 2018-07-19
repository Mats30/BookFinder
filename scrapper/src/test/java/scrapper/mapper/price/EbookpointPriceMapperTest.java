package scrapper.mapper.price;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


class EbookpointPriceMapperTest {
    private static final Logger LOG = LoggerFactory.getLogger(EbookpointPriceMapperTest.class);
    private static final String TEST_FILE_PATH = "src/test/resources/test_page.html";
    private static final String PARSED_BOOKS_FILE_PATH = "src/test/resources/parsed_books.html";
    private static final String PRESALE_CSS_CLASS = "classPresale";
    private static final String NEW_PRICE_KEY = "price";
    private PriceMapper mapper = new EbookpointPriceMapper();

    @Test
    void giveFileWithParsedDivs_ExpectScrappedPrices() {
        File parsedDivsFile = new File(PARSED_BOOKS_FILE_PATH);
        Elements parsedDivs = null;
        try {
            parsedDivs = Jsoup.parse(parsedDivsFile, "UTF-8").select(PRESALE_CSS_CLASS);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        System.out.println(parsedDivs);
        List<Double> newPricesList = parsedDivs.stream()
                .map(e -> mapper.scrapPrice(e, NEW_PRICE_KEY))
                .collect(Collectors.toList());
        List<Double> expectedPricesList = List.of(47.2, 43.91, 71.2, 14.32);
        assertThat(newPricesList).isEqualTo(expectedPricesList);

    }
}
