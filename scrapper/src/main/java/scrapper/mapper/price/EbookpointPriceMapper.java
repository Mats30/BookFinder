package scrapper.mapper.price;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
final class EbookpointPriceMapper implements PriceMapper {
    private static final String BOOK_CSS_CLASS = ".changeFormat2";
    private static final String DISCOUNT_ATTRIBUTE = "oszczedzasz";

    @Override
    public List<Double> parseBooksPrices(Elements bookContent) {
        return bookContent.stream()
                .map(e -> Double.parseDouble(e.attr(DISCOUNT_ATTRIBUTE)))
                .collect(Collectors.toList());
    }

    @Override
    public double scrapPrice(Element e, String attributePriceKey) {
        Elements bookContent = e.select(BOOK_CSS_CLASS);
        List<Double> prices = parseBooksPrices(bookContent);
        double maxValue = Collections.max(prices);
        return Double.parseDouble(bookContent.get(prices.indexOf(maxValue)).attr(attributePriceKey));
    }

}
