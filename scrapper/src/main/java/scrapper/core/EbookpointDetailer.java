package scrapper.core;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component
final class EbookpointDetailer implements Detailer {
    private static final String PAGES_NUMBER_CSS_CLASS = "pozycjaStronicowania";
    private static final String URL_ATTRIBUTE = "href";
    private static final String NEXT_PAGE_BUTTON_TEXT = ">";

    /**
     * @throws java.util.NoSuchElementException when document doesn't contain page number element
     * @param page base page
     * @return total number of pages
     */
    @Override
    public int scrapPagesNumber(Document page) {
        return page.getElementsByClass(PAGES_NUMBER_CSS_CLASS)
                .stream()
                .filter(e -> !e.text().equals(NEXT_PAGE_BUTTON_TEXT))
                .filter(e -> e.hasAttr(URL_ATTRIBUTE))
                .map(e -> Integer.valueOf(e.text()))
                .max((e1, e2) -> e1 > e2 ? e1 : e2)
                .orElseThrow();
    }
}
