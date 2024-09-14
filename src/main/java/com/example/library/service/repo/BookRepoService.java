package com.example.library.service.repo;

import com.example.library.dto.Book;
import com.example.library.models.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
@Service
public interface BookRepoService {

    BookEntity findById(Long id);

    Book saveBook(Book book);

    Book updateBook(BookEntity bookEntity);

    List<BookEntity> findAll();

    void deleteById(Long id);
}
