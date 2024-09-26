package com.example.library.repository;

import com.example.library.entity.AuthorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface AuthorRepository extends BaseRepository<AuthorEntity> {
    @Transactional
    AuthorEntity saveAndFlush(AuthorEntity authorEntity);
    @Transactional(readOnly = true)
    @Query("SELECT a FROM AuthorEntity a WHERE a.id IN (SELECT ba.authorId FROM book_author ba WHERE ba.bookId = :bookId)")
    List<AuthorEntity> findAuthorsByBookId(@Param("bookId") Long bookId);

    @Transactional(readOnly = true)
    @Query("SELECT ba.authorId FROM book_author ba WHERE ba.bookId = :bookId")
    Set<Long> findAuthorIdsByBookId(@Param("bookId") Long bookId);
}
