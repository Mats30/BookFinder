package model;

import java.util.Objects;

public class Book {
    private final String title;
    private final String author;
    private final long newPrice;
    private final long oldPrice;
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

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public long getNewPrice() {
        return newPrice;
    }

    public long getOldPrice() {
        return oldPrice;
    }

    public String getUrl() {
        return url;
    }

    public String getBookstore() {
        return bookstore;
    }

    public BookType getType() {
        return type;
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
                type == book.type;
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, author, newPrice, oldPrice, url, bookstore, type);
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
                ", type=" + type +
                '}';
    }


    public static class Builder {
        private String title;
        private String author;
        private long newPrice;
        private long oldPrice;
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

        public Builder withNewPrice(long newPrice) {
            this.newPrice = newPrice;
            return this;
        }

        public Builder withOldPrice(long oldPrice) {
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




}
