package com.example.library.controllers;

import com.example.library.dto.BookDTO;
import com.example.library.models.Author;
import com.example.library.models.Book;
import com.example.library.repos.BookRepository;
import com.example.library.services.AuthorService;
import com.example.library.services.BookService;
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
    public List<Book> getBooks() {
        return bookService.readAll();
    }

    @GetMapping("/sortedByName")
    public List<Book> getSortedBooksByName() {
        List<Book> books = bookService.readAll();
        books.sort(Comparator.comparing(Book::getTitle));
        return books;
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.readById(id);
    }

    @PostMapping()
    public Book createBook(@RequestBody BookDTO bookDTO) {
        Book book = new Book();
        List<Author> authors = new ArrayList<>();
        book.setTitle(bookDTO.getTitle());
        bookDTO.getAuthorIds().forEach(authorId -> {
            authors.add(authorService.readById(authorId));
        });
        return bookService.update(book);
    }
}
