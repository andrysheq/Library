package com.example.library.service;

import com.example.library.dto.Author;
import com.example.library.dto.Book;
import com.example.library.dto.request.BookRecord;
import com.example.library.dto.request.Request;
import com.example.library.dto.response.BookResponse;
import com.example.library.dto.response.FindBooksResponse;
import com.example.library.mapper.BaseMapper;
import com.example.library.entity.BookEntity;
import com.example.library.service.repo.BookRepoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
@Log4j2
public class BookService {
    private final BaseMapper mapper;
    private final BookRepoService bookRepoService;
    private final AuthorService authorService;
    @CachePut(value = "bookById", key = "#result.id")
    public Book addBook(Request<BookRecord> request) {
        BookRecord book = request.getPayload();

        return bookRepoService.saveBook(book);
    }
    @Cacheable(value = "bookById", key = "#id")
    public Book getBook(Long id) {
        BookEntity bookEntity = bookRepoService.findById(id);;

        return mapper.map(bookEntity, Book.class);
    }

    public FindBooksResponse findAllBooks() {
        List<BookEntity> bookList = bookRepoService.findAll();

        return FindBooksResponse.builder()
                .data(mapper.convertList(bookList, Book.class))
                .build();
    }
    @CachePut(value = "bookById", key = "#id")
    public Book updateBook(Long id, Request<BookRecord> request) {
        BookRecord book = request.getPayload();
        BookEntity bookEntity = bookRepoService.findById(id);
        bookEntity.setAuthorEntities(new HashSet<>(authorService.findAuthorsByIds(book.getAuthorIds())));
        bookEntity.setTitle(book.getTitle());
        bookEntity.setPageAmount(book.getPageAmount());

        return bookRepoService.updateBook(bookEntity);
    }
    @CacheEvict(value = "bookById", key = "#id")
    public void deleteBook(Long id) {
        bookRepoService.deleteById(id);
    }

}
