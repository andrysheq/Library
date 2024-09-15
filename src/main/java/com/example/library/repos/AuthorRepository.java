package com.example.library.repos;

import com.example.library.entity.AuthorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AuthorRepository extends BaseRepository<AuthorEntity> {
    @Transactional
    AuthorEntity saveAndFlush(AuthorEntity authorEntity);
}
