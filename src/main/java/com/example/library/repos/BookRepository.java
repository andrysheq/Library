package com.example.library.repos;

import com.example.library.models.Book;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Id> {
}
