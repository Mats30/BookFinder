package scrapper.service;

import model.Book;

import java.util.List;

public interface ScrapperService {
    List<Book> scrapAll();
    List<Book> scrap(int pageNumber);
}

