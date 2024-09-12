package com.example.library.repos;

import com.example.library.models.BookEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<BookEntity, Long> {
    List<BookEntity> findAll();
}
