package scrapper.service;

import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scrapper.connector.LibConnector;
import scrapper.core.Detailer;
import scrapper.core.Scrapper;
import scrapper.mapper.Mapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
final class EbookpointService implements ScrapperService {
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
        int numberOfPages = connector
                .connect(EBOOKPOINT_BASE_URL)
                .map(detailer::scrapPagesNumber)
                .orElse(0);

        return IntStream.rangeClosed(1, numberOfPages)
                .mapToObj(value -> scrapper.scrap(EBOOKPOINT_BASE_URL + value))
                .map(mapper::map)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> scrap(int pageNumber) {
        return Stream.of(scrapper.scrap(EBOOKPOINT_BASE_URL + pageNumber))
                .map(mapper::map)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
