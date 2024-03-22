package com.example.library.controllers;

import com.example.library.dto.AuthorDTO;
import com.example.library.models.Author;
import com.example.library.services.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/authors")
@Tag(
        name = "Контроллер для работы с авторами",
        description = "Все методы для работы с авторами"
)
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping()
    @Operation(summary = "Получить список всех авторов")
    public List<Author> getAuthors() {
        return authorService.readAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить автора по его ID")
    public Author getAuthorById(@PathVariable Long id) {
        return authorService.readById(id);
    }
    @GetMapping("/sorted-by-name")
    @Operation(summary = "Получить список авторов отсортированный по имени")
    public ResponseEntity<?> getSortedAuthorsByName() {
        List<Author> authors = authorService.readAll();
        if (!authors.isEmpty()) {
            authors.sort(Comparator.comparing(Author::getName));
            return ResponseEntity.ok(authors);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/sorted-by-gender")
    @Operation(summary = "Получить список авторов отсортированный по полу")
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
    @Operation(summary = "Добавить нового автора")
    public Author createAuthor(@RequestBody AuthorDTO authorDTO) {
        Author author = new Author();
        author.setName(authorDTO.getName());
        author.setGender(authorDTO.getGender());
        return authorService.update(author);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить автора по ID")
    public ResponseEntity<?> deleteAuthorById(@PathVariable Long id) {
        if (!authorService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        authorService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search/by-name")
    @Operation(summary = "Получить список авторов по имени")
    public ResponseEntity<?> searchAuthorByName(@RequestParam String name) {
        List<Author> authors = authorService.readByName(name);
        if (!authors.isEmpty()) {
            return ResponseEntity.ok(authors);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
