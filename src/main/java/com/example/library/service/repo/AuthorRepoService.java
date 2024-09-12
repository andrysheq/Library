package com.example.library.service.repo;

import com.example.library.dto.Author;
import com.example.library.dto.Book;
import com.example.library.models.AuthorEntity;
import com.example.library.models.BookEntity;

public interface AuthorRepoService {

    AuthorEntity findById(Long id);

//    Page<BookEntity> findBooksPageable(FindBooksRequest request);
//
//    List<BookEntity> findBooks(FindBooksRequest request);

    Author saveAuthor(Author author);

    Author updateAuthor(AuthorEntity authorEntity);

}
