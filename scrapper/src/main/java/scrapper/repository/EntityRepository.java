package scrapper.repository;


import java.util.List;

public interface EntityRepository<T> {
    T saveAll(List<T> listOfEntities);
}
