package scrapper.core;

import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import scrapper.connector.LibConnector;

@Component
final class EbookpointScrapper implements Scrapper {
    private static final String PRESALE_CSS_CLASS = "classPresale";
    private LibConnector connector;

    @Autowired
    public void setConnector(LibConnector connector) {
        this.connector = connector;
    }

    /**
     * @param content address of website to parse
     * @return elements containing books if present, else empty list of elements
     */
    @Override
    public Elements scrap(String content) {
        return connector
                .connect(content)
                .map(e -> e.getElementsByClass(PRESALE_CSS_CLASS))
                .orElse(new Elements().empty());
    }
}
