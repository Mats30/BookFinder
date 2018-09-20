package service;

import model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface BookService {
    ResponseEntity<Page<Book>> findPaginated(Pageable pageable);
}
