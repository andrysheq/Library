package com.example.library.service;

import com.example.library.dto.Author;
import com.example.library.dto.Book;
import com.example.library.models.AuthorEntity;
import com.example.library.models.BookEntity;
import com.example.library.repos.BookRepository;
import com.example.library.service.repo.AuthorRepoService;
import com.example.library.service.repo.BookRepoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class BookService {

    private final BookRepoService bookRepoService;
    private final AuthorService authorService;

    public List<BookEntity> findAllBooks() {
        return bookRepoService.findAll();
    }

    public BookEntity getBook(Long id) {
        return bookRepoService.findById(id);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

//    public BookEntity updateBookInformation(Long id, Book book) {
//        BookEntity bookEntityToUpdate = bookRepository.findById(id).orElse(null);
//        if (bookEntityToUpdate != null) {
//            bookEntityToUpdate.setTitle(book.getTitle());
//            List<AuthorEntity> newAuthorEntities = new ArrayList<>();
//            book.getAuthorIds().forEach(o-> {
//                if(authorService.existsById(o))
//                    newAuthorEntities.add(authorService.getAuthor(o));
//            });
//            if (!newAuthorEntities.isEmpty()) {
//                bookEntityToUpdate.setAuthorEntities(newAuthorEntities);
//                return bookRepository.save(bookEntityToUpdate);
//            }else{
//                return null;
//            }
//        }
//        return null;
//    }

//    public BookEntity create(Book book){
//        BookEntity bookEntity = new BookEntity();
//        bookEntity.setTitle(book.getTitle());
//        List<AuthorEntity> authorEntities = new ArrayList<>();
//        book.getAuthorIds().forEach(o-> {
//            if(authorService.existsById(o))
//                authorEntities.add(authorService.getAuthor(o));
//        });
//        if (!authorEntities.isEmpty()) {
//            bookEntity.setAuthorEntities(authorEntities);
//            return bookRepository.save(bookEntity);
//        }else{
//            return null;
//        }
//    }

    public Book updateBook(Book book) {
        return bookRepoService.updateBook(book);
    }

    public Book addBook(Book book){
        return bookRepoService.saveBook(book);
    }
}
