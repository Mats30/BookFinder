import model.Book;

import java.math.BigDecimal;

public class App {

    public static void main(String[] args) {
        createBook();
    }

    public static void createBook() {
        Book book = new Book.Builder()
                .withTitle("Potop")
                .withAuthor("Sienkiewicz")
                .withNewPrice(new BigDecimal(4999))
                .withOldPrice(new BigDecimal(6999))
                .build();
    }
}
