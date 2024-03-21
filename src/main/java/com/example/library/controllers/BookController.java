package com.example.library.controllers;

import com.example.library.dto.BookDTO;
import com.example.library.models.Author;
import com.example.library.models.Book;
import com.example.library.repos.BookRepository;
import com.example.library.services.AuthorService;
import com.example.library.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }
    @GetMapping()
    public ResponseEntity<?> getBooks() {
        List<Book> books = bookService.readAll();
        if (!books.isEmpty()) {
            return ResponseEntity.ok(books);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/by-name")
    public ResponseEntity<?> getSortedBooksByName() {
        List<Book> books = bookService.readAll();
        if (!books.isEmpty()) {
            books.sort(Comparator.comparing(Book::getTitle));
            return ResponseEntity.ok(books);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        Book book = bookService.readById(id);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public Book createBook(@RequestBody BookDTO bookDTO) {
        Book book = new Book();
        List<Author> authors = new ArrayList<>();
        book.setTitle(bookDTO.getTitle());
        bookDTO.getAuthorIds().forEach(authorId -> {
            authors.add(authorService.readById(authorId));
        });
        book.setAuthors(authors);
        return bookService.update(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable Long id) {
        if (!bookService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        bookService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
