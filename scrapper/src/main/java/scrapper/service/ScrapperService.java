package scrapper.service;

import model.Book;

import java.util.List;

public interface ScrapperService {
    List<Book> getAll();
    List<Book> get(int pageNumber);
}

