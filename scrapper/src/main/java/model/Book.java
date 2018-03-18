package model;

import java.math.BigDecimal;
import java.net.URL;

public class Book {
    private final String title;
    private final String author;
    private final BigDecimal newPrice;
    private final BigDecimal oldPrice;
    private final URL url;
    private final String bookstore;

    private Book(Builder bookBuilder) {
        title = bookBuilder.title;
        author = bookBuilder.author;
        newPrice = bookBuilder.newPrice;
        oldPrice = bookBuilder.oldPrice;
        url = bookBuilder.url;
        bookstore = bookBuilder.bookstore;
    }

    public static class Builder {
        private String title;
        private String author;
        private BigDecimal newPrice;
        private BigDecimal oldPrice;
        private URL url;
        private String bookstore;

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withAuthor(String author) {
            this.author = author;
            return this;
        }

        public Builder withNewPrice(BigDecimal newPrice) {
            this.newPrice = newPrice;
            return this;
        }

        public Builder withOldPrice(BigDecimal oldPrice) {
            this.oldPrice = oldPrice;
            return this;
        }

        public Builder withURL(URL url) {
            this.url = url;
            return this;
        }

        public Builder withBookStore(String bookStore) {
            this.bookstore = bookStore;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }

    @Override
    public String toString() {
        return String.format("%s - %s", this.author, this.title);
    }

}
