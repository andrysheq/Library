package com.example.library.repos;

import com.example.library.models.Author;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
