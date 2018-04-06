package scrapper.service;

import model.Book;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scrapper.connector.LibConnector;
import scrapper.core.Detailer;
import scrapper.core.Scrapper;
import scrapper.mapper.Mapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EbookpointService implements ScrapperService {
    private static final Logger LOG = LoggerFactory.getLogger(EbookpointService.class);
    private static final String EBOOKPOINT_BASE_URL = "https://ebookpoint.pl/kategorie/informatyka/";
    private final Detailer detailer;
    private final Scrapper scrapper;
    private final Mapper mapper;
    private final LibConnector connector;

    @Autowired
    public EbookpointService(Detailer detailer, Scrapper scrapper, Mapper mapper, LibConnector connector) {
        this.detailer = detailer;
        this.scrapper = scrapper;
        this.mapper = mapper;
        this.connector = connector;
    }

    @Override
    public List<Book> getAll() {
        List<Book> listOfAllBooks = new ArrayList<>();
        Optional<Document> document = connector.connect(EBOOKPOINT_BASE_URL);
        if (document.isPresent()) {
            Document doc = document.get();
            int numberOfPages = detailer.getPagesNumber(doc);
            try {
                for (int i = 1; i <= numberOfPages; i++) {
                    listOfAllBooks.addAll(mapper.map(scrapper.scrap(EBOOKPOINT_BASE_URL + i)));
                }
            } catch (IOException e) {
                LOG.error(e.getMessage());
                return listOfAllBooks;
            }
        } else {
            LOG.error("Error in fetching ebookpoint site");
        }
        return listOfAllBooks;
    }

    @Override
    public List<Book> get(int pageNumber) {
        List<Book> listOfAllBooks = new ArrayList<>();
        try {
            listOfAllBooks.addAll(mapper.map(scrapper.scrap(EBOOKPOINT_BASE_URL + pageNumber)));
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        return listOfAllBooks;
    }
}
