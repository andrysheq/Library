package com.example.library.services;

import com.example.library.models.Author;
import com.example.library.models.Book;
import com.example.library.repos.AuthorRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Service
public class AuthorService {
    private final EntityManager entityManager;
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository, EntityManager entityManager) {
        this.authorRepository = authorRepository;
        this.entityManager = entityManager;
    }

    public List<Author> readAll() {
        return authorRepository.findAll();
    }

    public Author readById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public Author update(Author author) {
        return authorRepository.save(author);
    }
    @Transactional
    public void deleteById(Long id) {
        List<Book> allBooks = entityManager.createQuery("SELECT b FROM Book b", Book.class).getResultList();


        Author authorToRemove = entityManager.find(Author.class, id);

        allBooks.forEach(o->{
            o.getAuthors().remove(authorToRemove);
            entityManager.merge(o);
        });

        authorRepository.deleteById(id);
    }
    public boolean existsById(Long id) {
        return authorRepository.existsById(id);
    }
    public List<Author> readByName(String name) {
        return authorRepository.findByNameContainingIgnoreCase(name);
    }
}
