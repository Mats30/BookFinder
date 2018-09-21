package controllers;

import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import service.BookService;

@RestController("/books")
public class BookController {
    private final BookPageResourceAssembler assembler;
    private final BookService service;

    @Autowired
    public BookController(BookPageResourceAssembler assembler, BookService service) {
        this.assembler = assembler;
        this.service = service;
    }

    @GetMapping
    public PagedResources<Resource<Book>> findScrappedBooks(final Pageable pageable) {
        return assembler.toResource(service.findPaginated(pageable));
    }
}
