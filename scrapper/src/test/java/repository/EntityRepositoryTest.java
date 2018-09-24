package repository;

import main.AppInitializer;
import model.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import providers.BooksProvider;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {AppInitializer.class})
@ExtendWith(SpringExtension.class)
@ActiveProfiles("dev")
class EntityRepositoryTest {

    @Autowired
    BookRepository repository;

    @Test
    void persistOneBook_ExpectFindAllWithSizeOne() {
        assertThat(repository.findAll().size()).isEqualTo(0);
        repository.save(BooksProvider.provideOneBook());
        assertThat(repository.findAll().size()).isEqualTo(1);
    }
}
