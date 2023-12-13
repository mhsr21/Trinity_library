package edu.trinity.library.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.trinity.library.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    
}
