package com.example.library.repos;

import com.example.library.models.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    Book findById(long id);
    List<Book> findAll();
}
