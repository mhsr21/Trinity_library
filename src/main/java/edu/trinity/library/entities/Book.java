package edu.trinity.library.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

// POJO Book

@Entity
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author is required")
    private String author;
    private String isbn;
    private LocalDate pubDate;

    public Book() {}

    public Book(String title, String author) {
        this(null, title, author, null, null);
    }
    public Book(String title, String author, String isbn) {
        this(null, title, author, isbn, null);
    }
    public Book(String title, String author, String isbn, LocalDate pubDate) {
        this(null, title, author, isbn, pubDate);
    }


    public Book(Long id, String title, String author, String isbn, LocalDate pubDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.pubDate = pubDate;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public LocalDate getPubDate() { return pubDate; }
    public void setPubDate(LocalDate pubDate) { this.pubDate = pubDate; }
    public void setPubDate(int year, int month, int dayOfMonth) {
        setPubDate(LocalDate.of(year, month, dayOfMonth));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id)
                && Objects.equals(title, book.title)
                && Objects.equals(author, book.author)
                && Objects.equals(isbn, book.isbn)
                && Objects.equals(pubDate, book.pubDate);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id,title,author,isbn,pubDate);
    }
}
