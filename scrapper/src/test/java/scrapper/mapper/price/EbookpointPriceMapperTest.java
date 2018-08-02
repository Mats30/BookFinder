package scrapper.mapper.price;

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


class EbookpointPriceMapperTest {
    private static final Logger LOG = LoggerFactory.getLogger(EbookpointPriceMapperTest.class);
    private static final String PARSED_BOOKS_FILE_PATH = "src/test/resources/ebookpoint_parsed_books.html";
    private static final String PRESALE_CSS_CLASS = ".classPresale";
    private static final String NEW_PRICE_KEY = "price";
    private static final String OLD_PRICE_KEY = "constprice";
    private PriceMapper mapper = new EbookpointPriceMapper();
    private File parsedDivsFile = new File(PARSED_BOOKS_FILE_PATH);

    @Test
    void giveFileWithParsedDivs_ExpectScrappedNewPrices() {
        Optional<Elements> parsedDivs = Optional.empty();
        try {
            parsedDivs = Optional.of(Jsoup.parse(parsedDivsFile, "UTF-8").select(PRESALE_CSS_CLASS));
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }

        List<Double> newPricesList = parsedDivs.stream()
                .flatMap(Collection::stream)
                .map(e -> mapper.scrapPrice(e, NEW_PRICE_KEY))
                .collect(Collectors.toList());
        List<Double> expectedPricesList = List.of(47.2, 43.91, 59.0, 71.2, 14.32);
        assertThat(newPricesList).isEqualTo(expectedPricesList);
    }

    @Test
    void giveFileWithParsedDivs_ExpectScrappedOldPrices() {
        Optional<Elements> parsedDivs = Optional.empty();
        try {
            parsedDivs = Optional.of(Jsoup.parse(parsedDivsFile, "UTF-8").select(PRESALE_CSS_CLASS));
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }

        List<Double> newPricesList = parsedDivs.stream()
                .flatMap(Collection::stream)
                .map(e -> mapper.scrapPrice(e, OLD_PRICE_KEY))
                .collect(Collectors.toList());
        List<Double> expectedPricesList = List.of(59.0, 54.89, 59.0, 89.0, 17.9);
        assertThat(newPricesList).isEqualTo(expectedPricesList);
    }

    @Test
    void giveListWithoutParsedDivs_ExpectEmptyPriceList() {
        Optional<Elements> parsedDivs = Optional.empty();
        List<Double> newPricesList = parsedDivs.stream()
                .flatMap(Collection::stream)
                .map(e -> mapper.scrapPrice(e, OLD_PRICE_KEY))
                .collect(Collectors.toList());
        List<Double> expectedPricesList = List.of();
        assertThat(newPricesList).isEqualTo(expectedPricesList);
    }
}
