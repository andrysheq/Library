package com.example.library.service.repo.impl;

import com.example.library.dto.Author;
import com.example.library.dto.Book;
import com.example.library.exception.RecordNotFoundException;
import com.example.library.models.AuthorEntity;
import com.example.library.models.BookEntity;
import com.example.library.repos.BookRepository;
import com.example.library.service.repo.AuthorRepoService;
import com.example.library.service.repo.BookRepoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
@Log4j2
public class BookRepoServiceImpl implements BookRepoService {

    private final BookRepository bookRepository;
    private final ModelMapper mapper;
    private final AuthorRepoService authorRepoService;

    @Override
    @Transactional(readOnly = true)
    public BookEntity findById(Long id) {
        Optional<BookEntity> bookOpt = bookRepository.findById(id);

        return bookOpt.orElseThrow(() -> new RecordNotFoundException("Книга не найдена. id: " + id));
    }

    @Override
    @Transactional
    public Book saveBook(Book book) {
          List<AuthorEntity> authors = authorRepoService.findByIds(book.getAuthorIds());
          BookEntity bookEntity = mapper.map(book, BookEntity.class);
          bookEntity.setAuthorEntities(authors.stream().collect(Collectors.toSet()));
          return mapper.map(bookRepository.save(bookEntity), Book.class);
    }

    @Override
    @Transactional
    public Book updateBook(BookEntity bookEntity) {
        return mapper.map(bookRepository.saveAndFlush(bookEntity), Book.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookEntity> findAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
