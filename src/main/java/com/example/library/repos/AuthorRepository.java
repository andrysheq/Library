package com.example.library.repos;

import com.example.library.models.Author;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    Author findById(long id);
    List<Author> findAll ();
}
