package controllers;

import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import scrapper.service.ScrapperService;

import java.util.List;

@RestController("/books")
public class BookController {

    private final ScrapperService scrapper;

    @Autowired
    public BookController(ScrapperService scrapper) {
        this.scrapper = scrapper;
    }

    @GetMapping("/books/{pageNumber}")
    public List<Book> getOnePageScrappedBooks(@PathVariable(name = "pageNumber") int pageNumber) {
        return scrapper.scrap(pageNumber);
    }

    @GetMapping
    public List<Book> getScrappedBooks() {
        return scrapper.scrapAll();
    }
}
