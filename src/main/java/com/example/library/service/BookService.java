package com.example.library.service;

import com.example.library.dto.Book;
import com.example.library.dto.request.Request;
import com.example.library.dto.response.FindBooksResponse;
import com.example.library.mapper.BaseMapper;
import com.example.library.models.BookEntity;
import com.example.library.service.repo.BookRepoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class BookService {
    private final BaseMapper mapper;
    private final BookRepoService bookRepoService;

    public Book addBook(Request<Book> request) {
        Book book = request.getPayload();

        return bookRepoService.saveBook(book);
    }

    public Book getBook(Long bookId) {
        BookEntity bookEntity = bookRepoService.findById(bookId);

        return mapper.map(bookEntity, Book.class);
    }

    public FindBooksResponse findAllBooks() {
        List<BookEntity> bookList = bookRepoService.findAll();

        return FindBooksResponse.builder()
                .data(mapper.convertList(bookList, Book.class))
                .build();
    }

    public Book updateBook(Long bookId, Request<Book> request) {
        Book book = request.getPayload();
        BookEntity bookEntity = bookRepoService.findById(bookId);
        Book oldBook = mapper.map(bookEntity, Book.class);
        Book savedBook = bookRepoService.updateBook(bookEntity);

        return savedBook;
    }

    public void deleteBook(Long bookId) {
        bookRepoService.deleteById(bookId);
    }

}
