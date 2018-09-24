package scrapper.core;

import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import scrapper.connector.LibConnector;

@Component
final class EbookpointScrapper implements Scrapper {
    private static final Logger LOG = LoggerFactory.getLogger(EbookpointScrapper.class);
    private static final String PRESALE_CSS_CLASS = "classPresale";
    private LibConnector connector;

    @Autowired
    EbookpointScrapper(LibConnector connector) {
        this.connector = connector;
    }

    /**
     * @param url address of website to parse
     * @return elements containing books if present, else empty list of elements
     */
    @Override
    public Elements scrap(String url) {
        return connector
                .connect(url)
                .map(e -> {
                    LOG.info(String.format("Scrapping %s", url));
                    return e.getElementsByClass(PRESALE_CSS_CLASS);
                })
                .orElse(new Elements().empty());
    }
}
