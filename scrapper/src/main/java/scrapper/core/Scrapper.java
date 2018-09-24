package scrapper.core;

import org.jsoup.select.Elements;

public interface Scrapper {
    Elements scrap(String url);
}
