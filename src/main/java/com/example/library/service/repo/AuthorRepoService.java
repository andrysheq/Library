package com.example.library.service.repo;

import com.example.library.dto.Author;
import com.example.library.dto.Book;
import com.example.library.models.AuthorEntity;
import com.example.library.models.BookEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface AuthorRepoService {
    AuthorEntity findById(Long id);
    Author saveAuthor(Author author);
    Author updateAuthor(Author author);
    List<AuthorEntity> findByIds(Iterable<Long> ids);
    List<AuthorEntity> findAll();
}
