package com.example.library.service.repo.impl;

import com.example.library.dto.Author;
import com.example.library.exception.RecordNotFoundException;
import com.example.library.models.AuthorEntity;
import com.example.library.repos.AuthorRepository;
import com.example.library.service.repo.AuthorRepoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthorRepoServiceImpl implements AuthorRepoService {
    private final AuthorRepository authorRepository;
    private final ModelMapper mapper;
    @Override
    public AuthorEntity findById(Long id) {
        Optional<AuthorEntity> authorOpt = authorRepository.findById(id);

        return authorOpt.orElseThrow(() -> new RecordNotFoundException("Автор не найден. id: " + id));
    }

    @Override
    public Author saveAuthor(Author author) {
        AuthorEntity authorEntity = mapper.map(author, AuthorEntity.class);
        return mapper.map(authorRepository.save(authorEntity), Author.class);
    }

    @Override
    public Author updateAuthor(Author author) {
        AuthorEntity authorEntity = mapper.map(author, AuthorEntity.class);
        return mapper.map(authorRepository.saveAndFlush(authorEntity), Author.class);
    }

    @Override
    public List<AuthorEntity> findByIds(Iterable<Long> ids){
        return authorRepository.findAllById(ids);
    }

    @Override
    public List<AuthorEntity> findAll() {
        return authorRepository.findAll();
    }
}
