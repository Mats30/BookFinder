package scrapper.core;

import org.jsoup.select.Elements;

import java.io.IOException;

public interface Scrapper {
    Elements scrap(String url) throws IOException;
}
