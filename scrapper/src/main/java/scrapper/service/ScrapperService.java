package scrapper.service;

import model.Book;

import java.util.List;

public interface ScrapperService {
    void scrapAll();
    List<Book> scrap(int pageNumber);
}

