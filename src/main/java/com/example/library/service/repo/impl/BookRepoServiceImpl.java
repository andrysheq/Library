package com.example.library.service.repo.impl;

import com.example.library.dto.Book;
import com.example.library.dto.request.BookRecord;
import com.example.library.entity.BookEntity;
import com.example.library.exception.RecordNotFoundException;
import com.example.library.repository.BookRepository;
import com.example.library.service.AuthorService;
import com.example.library.service.repo.BookRepoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Log4j2
public class BookRepoServiceImpl implements BookRepoService {

    private final BookRepository bookRepository;
    private final ModelMapper mapper;
    private final AuthorService authorService;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "bookById", key = "#id")
    public BookEntity findById(Long id) {
        Optional<BookEntity> bookOpt = bookRepository.findById(id);

        return bookOpt.orElseThrow(() -> new RecordNotFoundException("Книга не найдена. id: " + id));
    }

    @Override
    @Transactional
    @CachePut(value = "bookById", key = "#result.id")
    public BookEntity saveBook(BookRecord book) {
        BookEntity bookEntity = mapper.map(book, BookEntity.class);
        Long BookId = bookEntity.getId();

        Set<Long> authorIds = book.getAuthorIds();
        if (authorIds != null && !authorIds.isEmpty()) {
            for (Long authorId : authorIds) {
                bookRepository.saveBookAuthor(BookId, authorId);
            }
        }
        return bookRepository.save(bookEntity);
    }

    @Override
    @Transactional
    @CachePut(value = "bookById", key = "#bookEntity.id")
    public BookEntity updateBook(BookEntity bookEntity) {
        return bookRepository.saveAndFlush(bookEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookEntity> findAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    @CacheEvict(value = "bookById", key = "#id")
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
