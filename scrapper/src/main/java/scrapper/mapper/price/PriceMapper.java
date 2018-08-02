package scrapper.mapper.price;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public interface PriceMapper {
    List<Double> parseBooksPrices(Elements bookContent);
    double scrapPrice(Element e, String attributePriceKey);
}
