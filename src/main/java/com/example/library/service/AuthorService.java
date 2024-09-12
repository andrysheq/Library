package com.example.library.service;

import com.example.library.dto.Author;
import com.example.library.models.AuthorEntity;
import com.example.library.models.BookEntity;
import com.example.library.repos.AuthorRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    private final EntityManager entityManager;
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository, EntityManager entityManager) {
        this.authorRepository = authorRepository;
        this.entityManager = entityManager;
    }

    public List<AuthorEntity> readAll() {
        return authorRepository.findAll();
    }

    public AuthorEntity readById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    //Используем для удаления связей у авторов и книг (при удалении автора, у всех книг удаляется этот автор)
    @Transactional
    public void deleteById(Long id) {
        List<BookEntity> allBookEntities = entityManager.createQuery("SELECT b FROM BookEntity b", BookEntity.class).getResultList();


        AuthorEntity authorEntityToRemove = entityManager.find(AuthorEntity.class, id);

        allBookEntities.forEach(o->{
            o.getAuthorEntities().remove(authorEntityToRemove);
            entityManager.merge(o);
        });

        authorRepository.deleteById(id);
    }
    public boolean existsById(Long id) {
        return authorRepository.existsById(id);
    }
    public List<AuthorEntity> readByName(String name) {
        return authorRepository.findByNameContainingIgnoreCase(name);
    }

    public void clear(){
        authorRepository.deleteAll();
    }

    public AuthorEntity updateAuthorInformation(Long id, Author author) {
        AuthorEntity authorEntity = authorRepository.findById(id).orElse(null);
        //Не может быть null, так как проверяем в контроллере, перед тем как вызвать метод
        authorEntity.setName(author.getName());
        authorEntity.setGender(author.getGender());

        return authorRepository.save(authorEntity);
    }

    public AuthorEntity create(Author author){
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setName(author.getName());
        authorEntity.setGender(author.getGender());
        return authorRepository.save(authorEntity);
    }
}
