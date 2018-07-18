package scrapper.service;

import model.Book;

import java.util.List;

public interface ScrapperService {
    List<Book> scrappAll();
    List<Book> scrapp(int pageNumber);
}

