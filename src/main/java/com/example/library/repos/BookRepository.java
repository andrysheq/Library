package com.example.library.repos;

import com.example.library.models.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    List<BookEntity> findAll();
    BookEntity saveAndFlush(BookEntity bookEntity);
}
