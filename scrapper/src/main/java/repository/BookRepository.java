package repository;

import model.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
class BookRepository implements EntityRepository<Book> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Book save(Book entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public List<Book> saveAll(List<Book> listOfEntities) {
        em.persist(listOfEntities);
        return listOfEntities;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Book> findAll() {
        return em.createQuery("Select t from " + Book.class.getSimpleName() + " t").getResultList();
    }
}
