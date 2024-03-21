package com.example.library.repos;

import com.example.library.models.Author;
import com.example.library.models.Book;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    Book findById(long id);
    List<Book> findAll();
}
