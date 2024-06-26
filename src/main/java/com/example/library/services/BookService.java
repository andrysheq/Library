package com.example.library.services;

import com.example.library.dto.BookDTO;
import com.example.library.models.Author;
import com.example.library.models.Book;
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

    public List<Book> readAll() {
        return bookRepository.findAll();
    }

    public Book readById(Long id) {
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

    public Book updateBookInformation(Long id, BookDTO bookDTO) {
        Book bookToUpdate = bookRepository.findById(id).orElse(null);
        if (bookToUpdate != null) {
            bookToUpdate.setTitle(bookDTO.getTitle());
            List<Author> newAuthors = new ArrayList<>();
            bookDTO.getAuthorIds().forEach(o-> {
                if(authorService.existsById(o))
                    newAuthors.add(authorService.readById(o));
            });
            if (!newAuthors.isEmpty()) {
                bookToUpdate.setAuthors(newAuthors);
                return bookRepository.save(bookToUpdate);
            }else{
                return null;
            }
        }
        return null;
    }

    public Book create(BookDTO bookDTO){
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        List<Author> authors = new ArrayList<>();
        bookDTO.getAuthorIds().forEach(o-> {
            if(authorService.existsById(o))
                authors.add(authorService.readById(o));
        });
        if (!authors.isEmpty()) {
            book.setAuthors(authors);
            return bookRepository.save(book);
        }else{
            return null;
        }
    }
}
