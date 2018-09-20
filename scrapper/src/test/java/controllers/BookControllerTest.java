package controllers;

import com.google.gson.Gson;
import model.Book;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import providers.BooksProvider;
import repository.BookRepository;
import service.BookService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

class BookControllerTest {
    private static final Logger LOG = LoggerFactory.getLogger(BookControllerTest.class);
    private MockMvc mockMvc;
    private Pageable pageRequest;
    private BookService service;
    private Gson gson;

    @BeforeAll
    void setUp() {
        service = mock(BookService.class);
        mockMvc = standaloneSetup(new BookController(service)).build();
        gson = new Gson();
    }

    @Test
    void getFirstPageWithTwoBooks_ExpectResponseStatusOkWithProperResponseBody() {
        int pageNumber = 1;
        int limit = 2;
        Pageable pageableRequest = PageRequest.of(pageNumber, limit);
        Page<Book> responsePage = new PageImpl<>(
                ResponseEntity.ok(BooksProvider.provideAllBooksFromEbookpoint()),
                pageableRequest,
                4);
        when(service.findPaginated(pageableRequest)).thenReturn(responsePage);
        try {
            mockMvc.perform(get("/books")
                    .param("page", String.valueOf(pageNumber))
                    .param("limit", String.valueOf(limit)))
                    .andExpect(content()
                            .json(gson.toJson(BooksProvider.provideAllBooksFromEbookpoint()))
                    )
                    .andExpect(status().isOk());
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
