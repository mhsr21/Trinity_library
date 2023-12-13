package edu.trinity.library.controllers;

import edu.trinity.library.entities.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Transactional
class BookControllerTest {
    @Autowired
    private BookController bookController;

    @Test
    void getAllBooks() {
        List<Book> allBooks = bookController.getAllBooks();
        assertEquals(2, allBooks.size());
    }

    @Test
    void getBooksById() {
        ResponseEntity<Book> response = bookController.getBooksById(Long.valueOf(1));
        assertEquals(true, response.hasBody());
        assertEquals("Fanon, Frantz", response.getBody().getAuthor());


    }

    @Test
    void deleteBooksById() {
        ResponseEntity<Book> response = bookController.deleteBooksById(Long.valueOf(1));
        assertEquals(false, response.hasBody());
        List<Book> allBooks = bookController.getAllBooks();
        assertEquals(1, allBooks.size());
    }

    @Test
    void addNewBook() {
        ResponseEntity<Book> response = bookController.addNewBook(new Book("Black Skin, White Masks", "Fanon, Frantz"));
        assertEquals(true, response.hasBody());
        assertEquals("Black Skin, White Masks", response.getBody().getTitle());
        List<Book> allBooks = bookController.getAllBooks();
        assertEquals(3, allBooks.size());

    }

    @Test
    void updateBook() {
        ResponseEntity<Book> response = bookController.updateBook(Long.valueOf(2), new Book("Post-Scarcity Anarchism", "Bookchin, Murray", "087867005X", LocalDate.of(1971,1,1)));
        assertEquals(true, response.hasBody());
        assertEquals("Post-Scarcity Anarchism", response.getBody().getTitle());
    }
}