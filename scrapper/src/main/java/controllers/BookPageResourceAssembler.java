package controllers;

import model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Component;

@Component
class BookPageResourceAssembler {
    private final PagedResourcesAssembler<Book> assembler;

    BookPageResourceAssembler() {
        assembler = new PagedResourcesAssembler<>(null, null);
    }

    PagedResources<Resource<Book>> toResource(Page<Book> entity) {
        return assembler.toResource(entity);
    }
}
