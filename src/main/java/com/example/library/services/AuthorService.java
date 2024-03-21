package com.example.library.services;

import com.example.library.models.Author;
import com.example.library.repos.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> readAll() {
        return authorRepository.findAll();
    }

    public Author readById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public Author update(Author author) {
        return authorRepository.save(author);
    }

    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}
