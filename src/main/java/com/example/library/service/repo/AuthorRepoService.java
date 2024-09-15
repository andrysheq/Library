package com.example.library.service.repo;

import com.example.library.dto.Author;
import com.example.library.dto.request.AuthorRecord;
import com.example.library.entity.AuthorEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorRepoService {
    AuthorEntity findById(Long id);
    Author saveAuthor(AuthorRecord author);
    Author updateAuthor(AuthorEntity authorEntity);
    List<AuthorEntity> findByIds(Iterable<Long> ids);
    List<AuthorEntity> findAll();
    void deleteById(Long id);
}
