package repository;


import java.util.List;

public interface EntityRepository<T> {
    T save(T entity);
    List<T> saveAll(List<T> listOfEntities);
    List<T> findAll();
}
