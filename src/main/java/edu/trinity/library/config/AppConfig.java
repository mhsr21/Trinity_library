package edu.trinity.library.config;

import edu.trinity.library.dao.BookRepository;
import edu.trinity.library.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class AppConfig implements CommandLineRunner {
    private final BookRepository repository;

    @Autowired
    public AppConfig(BookRepository repository) { this.repository = repository; }

    @Override
    public void run(String... args) throws Exception {
        repository.saveAll(
                List.of(
                        new Book("The Wretched of the Earth", "Fanon, Frantz", "0802150837", LocalDate.of(1965, 1, 1)),
                        new Book("Women, Race and Class", "Davis, Angela", "0394510399", LocalDate.of(1981,1,1))
                )
        );

    }
}
