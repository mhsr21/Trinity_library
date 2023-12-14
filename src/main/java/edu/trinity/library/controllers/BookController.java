package edu.trinity.library.controllers;

import org.springframework.web.bind.annotation.RestController;
import edu.trinity.library.dao.BookRepository;
import edu.trinity.library.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class BookController {
    private final BookRepository repository;

    @Autowired // Do you have a book repository bean in the application context? if so, supply a reference
    public BookController (BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping({"/books", "/books/"}) // http://localhost:8080/books
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    @GetMapping("/books/{id}") // HTTP template variable
    public ResponseEntity<Book> getBooksById(@PathVariable Long id) {
        return ResponseEntity.of(repository.findById(id));
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Book> deleteBooksById(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/books") // Everything that has to do with DB must be wrapped around with transaction
    public ResponseEntity<Book> addNewBook(@RequestBody Book book) { // Transaction--all or nothing
        repository.save(book);
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{ID}")
                .buildAndExpand(book.getId())
                .toUri();
        return ResponseEntity.created(location).body(book);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        return repository.findById(id)
                .map(p -> {
                    p.setTitle(book.getTitle());
                    p.setAuthor(book.getAuthor());
                    p.setIsbn(book.getIsbn());
                    p.setPubDate(book.getPubDate());
                    return ResponseEntity.ok(repository.save(p));
                })
                .orElse(ResponseEntity.notFound().build());
    }





}
