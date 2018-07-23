package controllers;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.MockMvc;
import providers.BooksProvider;
import scrapper.service.ScrapperService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

class BookControllerTest {
    private static final Logger LOG = LoggerFactory.getLogger(BookControllerTest.class);

    @Test
    void tryToGetOnePageOfBooks_ExpectProperJSON() {
        Gson gson = new Gson();
        ScrapperService service = mock(ScrapperService.class);
        when(service.scrapAll()).thenReturn(BooksProvider.provideAllBooksFromEbookpoint());
        BookController bookController = new BookController(service);
        MockMvc mockMvc = standaloneSetup(bookController).build();
        try {
            mockMvc.perform(get("/books"))
                    .andExpect(content().json(gson
                    .toJson(BooksProvider.provideAllBooksFromEbookpoint())));
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
