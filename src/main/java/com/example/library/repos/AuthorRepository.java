package com.example.library.repos;

import com.example.library.models.Author;
import com.example.library.models.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    Author findById(long id);
    List<Author> findAll ();

    List<Author> findByNameContainingIgnoreCase(String title);
}
