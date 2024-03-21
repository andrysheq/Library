package com.example.library.services;

import com.example.library.models.Author;
import com.example.library.models.Book;
import com.example.library.repos.AuthorRepository;
import com.example.library.repos.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> readAll() {
        return bookRepository.findAll();
    }

    public Book readById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book update(Book book) {
        return bookRepository.save(book);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return bookRepository.existsById(id);
    }
}
