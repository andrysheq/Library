package com.example.library.controllers;

import com.example.library.models.Author;
import com.example.library.models.Book;
import com.example.library.repos.BookRepository;
import com.example.library.services.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
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
}
