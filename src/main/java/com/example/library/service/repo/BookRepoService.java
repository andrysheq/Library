package com.example.library.service.repo;

import com.example.library.dto.Book;
import com.example.library.dto.request.BookRecord;
import com.example.library.entity.BookEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookRepoService {

    BookEntity findById(Long id);

    Book saveBook(BookRecord book);

    Book updateBook(BookEntity bookEntity);

    List<BookEntity> findAll();

    void deleteById(Long id);
}
