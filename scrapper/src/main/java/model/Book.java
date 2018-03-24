package model;

import java.math.BigDecimal;
import java.util.Objects;

public class Book {
    private final String title;
    private final String author;
    private final BigDecimal newPrice;
    private final BigDecimal oldPrice;
    private final String url;
    private final String bookstore;
    private final BookType type;

    private Book(Builder bookBuilder) {
        title = bookBuilder.title;
        author = bookBuilder.author;
        newPrice = bookBuilder.newPrice;
        oldPrice = bookBuilder.oldPrice;
        url = bookBuilder.url;
        bookstore = bookBuilder.bookstore;
        type = bookBuilder.type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(newPrice, book.newPrice) &&
                Objects.equals(oldPrice, book.oldPrice) &&
                Objects.equals(url, book.url) &&
                Objects.equals(bookstore, book.bookstore) &&
                type == book.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, newPrice, oldPrice, url, bookstore, type);
    }


    public static class Builder {
        private String title;
        private String author;
        private BigDecimal newPrice;
        private BigDecimal oldPrice;
        private String url;
        private String bookstore;
        private BookType type;

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

        public Builder withURL(String url) {
            this.url = url;
            return this;
        }

        public Builder withBookStore(String bookStore) {
            this.bookstore = bookStore;
            return this;
        }

        public Builder withType(BookType type) {
            this.type = type;
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
