package scrapper.repository;

import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
class BookRepository implements EntityRepository<Book> {

    private final EntityManagerFactory emf;

    @Autowired
    public BookRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }


    @Override
    public Book saveAll(List<Book> listOfEntities) {
        return null;
    }
}
