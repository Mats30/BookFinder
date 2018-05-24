package scrapper.core;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class EbookpointDetailer implements Detailer {

    public static final String PAGES_NUMBER_CLASS = "pozycjaStronicowania";

    @Override
    public int getPagesNumber(Document page) {
        Optional<Element> element = page.getElementsByClass(PAGES_NUMBER_CLASS)
                .stream()
                .filter(o -> !o.text().equals(">"))
                .filter(o -> o.hasAttr("href"))
                .max((o1, o2) -> Integer.valueOf(o1.text()) > Integer.valueOf(o2.text()) ? Integer.valueOf(o1.text()) : Integer.valueOf(o2.text()));
        return Integer.parseInt(element.get().text());
    }
}
