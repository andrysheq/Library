package com.example.library.repos;

import com.example.library.models.AuthorEntity;
import com.example.library.models.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
    List<AuthorEntity> findAll ();
    List<AuthorEntity> findByNameContainingIgnoreCase(String title);
    List<AuthorEntity> findAllById(Iterable<Long> ids);
    AuthorEntity saveAndFlush(AuthorEntity authorEntity);
}
