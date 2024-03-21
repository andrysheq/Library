package com.example.library.controllers;

import com.example.library.dto.AuthorDTO;
import com.example.library.dto.BookDTO;
import com.example.library.models.Author;
import com.example.library.models.Book;
import com.example.library.repos.AuthorRepository;
import com.example.library.services.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping()
    public List<Author> getAuthors() {
        return authorService.readAll();
    }

    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable Long id) {
        return authorService.readById(id);
    }
    @GetMapping("/by-title")
    public ResponseEntity<?> getSortedAuthorsByName() {
        List<Author> authors = authorService.readAll();
        if (!authors.isEmpty()) {
            authors.sort(Comparator.comparing(Author::getName));
            return ResponseEntity.ok(authors);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/by-gender")
    public ResponseEntity<?> getSortedAuthorsByGender() {
        List<Author> authors = authorService.readAll();
        if (!authors.isEmpty()) {
            authors.sort(Comparator.comparing(Author::getGender));
            return ResponseEntity.ok(authors);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping()
    public Author createAuthor(@RequestBody AuthorDTO authorDTO) {
        Author author = new Author();
        author.setName(authorDTO.getName());
        author.setGender(authorDTO.getGender());
        return authorService.update(author);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthorById(@PathVariable Long id) {
        if (!authorService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        authorService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search/by-name")
    public ResponseEntity<?> searchAuthorByName(@RequestParam String name) {
        List<Author> authors = authorService.readByName(name);
        if (!authors.isEmpty()) {
            return ResponseEntity.ok(authors);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
