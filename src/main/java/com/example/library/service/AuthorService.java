package com.example.library.service;

import com.example.library.dto.Author;
import com.example.library.models.AuthorEntity;
import com.example.library.service.repo.AuthorRepoService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthorService {
    private final EntityManager entityManager;
    private final AuthorRepoService authorRepoService;

    public List<AuthorEntity> getAllAuthors() {
        return authorRepoService.findAll();
    }

    public AuthorEntity getAuthor(Long id) {
        return authorRepoService.findById(id);
    }

    //Используем для удаления связей у авторов и книг (при удалении автора, у всех книг удаляется этот автор)
//    @Transactional
//    public void deleteById(Long id) {
//        List<BookEntity> allBookEntities = entityManager.createQuery("SELECT b FROM BookEntity b", BookEntity.class).getResultList();
//
//
//        AuthorEntity authorEntityToRemove = entityManager.find(AuthorEntity.class, id);
//
//        allBookEntities.forEach(o->{
//            o.getAuthorEntities().remove(authorEntityToRemove);
//            entityManager.merge(o);
//        });
//
//        authorRepository.deleteById(id);
//    }

    public Author updateAuthor(Author author) {
        return authorRepoService.updateAuthor(author);
    }

    public Author addAuthor(Author author){
        return authorRepoService.saveAuthor(author);
    }
}
