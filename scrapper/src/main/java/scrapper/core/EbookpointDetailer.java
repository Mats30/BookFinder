package scrapper.core;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class EbookpointDetailer implements Detailer {

    @Override
    public int getPagesNumber(Document page) {
        Optional<Element> element = page.getElementsByClass("pozycjaStronicowania")
                .stream()
                .filter(o -> !o.text().equals(">"))
                .filter(o -> o.hasAttr("href"))
                .max((o1, o2) -> Integer.valueOf(o1.text()) > Integer.valueOf(o2.text()) ? Integer.valueOf(o1.text()) : Integer.valueOf(o2.text()));
        return Integer.parseInt(element.get().text());

    }
}
