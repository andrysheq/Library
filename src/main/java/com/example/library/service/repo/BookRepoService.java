package com.example.library.service.repo;

import com.example.library.dto.Book;
import com.example.library.models.BookEntity;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

public interface BookRepoService {

    BookEntity findById(Long id);

//    Page<BookEntity> findBooksPageable(FindBooksRequest request);
//
//    List<BookEntity> findBooks(FindBooksRequest request);

    Book saveBook(Book book);

    Book updateBook(BookEntity bookEntity);

}
