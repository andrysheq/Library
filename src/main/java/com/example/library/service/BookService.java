package com.example.library.service;

import com.example.library.dto.Book;
import com.example.library.models.AuthorEntity;
import com.example.library.models.BookEntity;
import com.example.library.repos.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookService(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    public List<BookEntity> readAll() {
        return bookRepository.findAll();
    }

    public BookEntity readById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return bookRepository.existsById(id);
    }

    public void clear(){
        bookRepository.deleteAll();
    }

    public BookEntity updateBookInformation(Long id, Book book) {
        BookEntity bookEntityToUpdate = bookRepository.findById(id).orElse(null);
        if (bookEntityToUpdate != null) {
            bookEntityToUpdate.setTitle(book.getTitle());
            List<AuthorEntity> newAuthorEntities = new ArrayList<>();
            book.getAuthorIds().forEach(o-> {
                if(authorService.existsById(o))
                    newAuthorEntities.add(authorService.readById(o));
            });
            if (!newAuthorEntities.isEmpty()) {
                bookEntityToUpdate.setAuthorEntities(newAuthorEntities);
                return bookRepository.save(bookEntityToUpdate);
            }else{
                return null;
            }
        }
        return null;
    }

    public BookEntity create(Book book){
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(book.getTitle());
        List<AuthorEntity> authorEntities = new ArrayList<>();
        book.getAuthorIds().forEach(o-> {
            if(authorService.existsById(o))
                authorEntities.add(authorService.readById(o));
        });
        if (!authorEntities.isEmpty()) {
            bookEntity.setAuthorEntities(authorEntities);
            return bookRepository.save(bookEntity);
        }else{
            return null;
        }
    }
}
