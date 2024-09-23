package com.example.library.repository;

import com.example.library.entity.AuthorEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AuthorRepository extends BaseRepository<AuthorEntity> {
    @Transactional
    AuthorEntity saveAndFlush(AuthorEntity authorEntity);
}
