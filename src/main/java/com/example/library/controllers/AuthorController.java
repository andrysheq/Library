package com.example.library.controllers;

import com.example.library.models.Author;
import com.example.library.repos.AuthorRepository;
import com.example.library.services.AuthorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors/sortedByGender")
    public List<Author> getSortedAuthorsByGender() {
        List<Author> authors = authorService.readAll();
        authors.sort(Comparator.comparing(Author::getGender));
        return authors;
    }

    @GetMapping("/authors/{id}")
    public Author getAuthorById(@PathVariable Long id) {
        return authorService.readById(id);
    }
    @GetMapping("/authors/sortedByName")
    public List<Author> getSortedAuthorsByName() {
        List<Author> authors = authorService.readAll();
        authors.sort(Comparator.comparing(Author::getName));
        return authors;
    }
}
