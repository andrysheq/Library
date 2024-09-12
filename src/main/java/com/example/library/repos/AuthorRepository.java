package com.example.library.repos;

import com.example.library.models.AuthorEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {
    List<AuthorEntity> findAll ();

    List<AuthorEntity> findByNameContainingIgnoreCase(String title);
}
