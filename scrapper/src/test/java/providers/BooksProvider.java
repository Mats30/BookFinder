package providers;

import model.Book;
import model.BookType;

import java.util.List;

public class BooksProvider {

    public static List<Book> provideAllBooksFromEbookpoint() {
        Book first = new Book.Builder()
                .withAuthor("Karol Nienartowicz")
                .withTitle("Górskie wyprawy fotograficzne")
                .withBookStore("ebookpoint")
                .withURL("//ebookpoint.pl/ksiazki/gorskie-wyprawy-fotograficzne-karol-nienartowicz,gowyfo.htm")
                .withType(BookType.MOBI_EPUB_PDF)
                .withOldPrice(20.0)
                .withNewPrice(10.0)
                .build();
        Book second = new Book.Builder()
                .withAuthor("Aditya Bhargava")
                .withTitle("Algorytmy. Ilustrowany przewodnik")
                .withBookStore("ebookpoint")
                .withURL("//ebookpoint.pl/ksiazki/algorytmy-ilustrowany-przewodnik-aditya-bhargava,algoip.htm")
                .withType(BookType.MOBI_EPUB_PDF)
                .withOldPrice(20.0)
                .withNewPrice(10.0)
                .build();

        Book third = new Book.Builder()
                .withAuthor("Matt Zandstra")
                .withTitle("PHP. Obiekty, wzorce, narzędzia. Wydanie V")
                .withBookStore("ebookpoint")
                .withURL("//ebookpoint.pl/ksiazki/php-obiekty-wzorce-narzedzia-wydanie-v-matt-zandstra,phpob5.htm")
                .withType(BookType.MOBI_EPUB_PDF)
                .withOldPrice(20.0)
                .withNewPrice(10.0)
                .build();

        Book fourth = new Book.Builder()
                .withAuthor("Adam Józefiok")
                .withTitle("Windows 7 PL. Pierwsza pomoc")
                .withBookStore("ebookpoint")
                .withURL("//ebookpoint.pl/ksiazki/windows-7-pl-pierwsza-pomoc-adam-jozefiok,win7pp.htm")
                .withType(BookType.MOBI_EPUB_PDF)
                .withOldPrice(20.0)
                .withNewPrice(10.0)
                .build();

        return List.of(first, second, third, fourth);
    }
}
