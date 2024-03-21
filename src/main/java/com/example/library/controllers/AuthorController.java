package com.example.library.controllers;

import com.example.library.models.Author;
import com.example.library.repos.AuthorRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping("/authors/sortedByGender")
    public List<Author> getSortedAuthorsByGender() {
        List<Author> authors = authorRepository.findAll();
        authors.sort(Comparator.comparing(Author::getGender));
        return authors;
    }

    @GetMapping("/authors/{id}")
    public Author getAuthorById(@PathVariable Long id) {
        return authorRepository.findById(id).orElse(null);
    }
    @GetMapping("/authors/sortedByName")
    public List<Author> getSortedAuthorsByName() {
        List<Author> authors = authorRepository.findAll();
        authors.sort(Comparator.comparing(Author::getName));
        return authors;
    }
}
