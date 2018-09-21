package service;

import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import repository.BookRepository;

@Service
class BookServiceImpl implements BookService {

    private final BookRepository repository;

    @Autowired
    BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Book> findPaginated(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
