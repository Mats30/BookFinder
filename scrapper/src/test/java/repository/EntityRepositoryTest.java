package repository;

import model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import providers.BooksProvider;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("dev")
class EntityRepositoryTest {

    @Autowired
    EntityRepository<Book> repository;

    @Test
    void persistOneBook_ExpectFindAllWithSizeOne() {
        repository.save(BooksProvider.provideOneBook());
        assertThat(repository.findAll().size()).isEqualTo(1);
    }
}
