package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Book {
    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private final String title;
    private final String author;
    @Column(name = "new_price", nullable = false)
    private final double newPrice;
    @Column(name = "old_price", nullable = false)
    private final double oldPrice;
    @Column(nullable = false)
    private final String url;
    @Column(nullable = false)
    private final String bookstore;
    @Enumerated(EnumType.STRING)
    private final BookType bookType;

    private Book() {
        this.title = null;
        this.author = null;
        this.newPrice = 0;
        this.oldPrice = 0;
        this.url = null;
        this.bookstore = null;
        this.bookType = null;
    }

    private Book(Builder bookBuilder) {
        title = bookBuilder.title;
        author = bookBuilder.author;
        newPrice = bookBuilder.newPrice;
        oldPrice = bookBuilder.oldPrice;
        url = bookBuilder.url;
        bookstore = bookBuilder.bookstore;
        bookType = bookBuilder.bookType;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public String getUrl() {
        return url;
    }

    public String getBookstore() {
        return bookstore;
    }

    public BookType getBookType() {
        return bookType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return newPrice == book.newPrice &&
                oldPrice == book.oldPrice &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(url, book.url) &&
                Objects.equals(bookstore, book.bookstore) &&
                bookType == book.bookType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, newPrice, oldPrice, url, bookstore, bookType);
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", newPrice=" + newPrice +
                ", oldPrice=" + oldPrice +
                ", url='" + url + '\'' +
                ", bookstore='" + bookstore + '\'' +
                ", bookType=" + bookType +
                '}';
    }


    public static class Builder {
        private String title;
        private String author;
        private double newPrice;
        private double oldPrice;
        private String url;
        private String bookstore;
        private BookType bookType;

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withAuthor(String author) {
            this.author = author;
            return this;
        }

        public Builder withNewPrice(double newPrice) {
            this.newPrice = newPrice;
            return this;
        }

        public Builder withOldPrice(double oldPrice) {
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
            this.bookType = type;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }
}
