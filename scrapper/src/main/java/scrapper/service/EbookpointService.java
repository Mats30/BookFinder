package scrapper.service;

import model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scrapper.connector.LibConnector;
import scrapper.core.Detailer;
import scrapper.core.Scrapper;
import scrapper.mapper.Mapper;

import java.util.ArrayList;
import java.util.List;

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
    public List<Book> scrapAll() {
        List<Book> listOfAllBooks = new ArrayList<>();
        int numberOfPages = connector
                .connect(EBOOKPOINT_BASE_URL)
                .map(detailer::scrapPagesNumber)
                .orElse(0);

        for (int i = 1; i <= numberOfPages; i++) {
            listOfAllBooks.addAll(mapper.map(scrapper.scrap(EBOOKPOINT_BASE_URL + i)));
        }

        return listOfAllBooks;
    }

    @Override
    public List<Book> scrap(int pageNumber) {
        List<Book> listOfAllBooks = new ArrayList<>();
        listOfAllBooks.addAll(mapper.map(scrapper.scrap(EBOOKPOINT_BASE_URL + pageNumber)));
        return listOfAllBooks;
    }
}
