package com.example.library.service.repo.impl;

import com.example.library.dto.Author;
import com.example.library.dto.request.AuthorRecord;
import com.example.library.exception.RecordNotFoundException;
import com.example.library.entity.AuthorEntity;
import com.example.library.repos.AuthorRepository;
import com.example.library.service.repo.AuthorRepoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthorRepoServiceImpl implements AuthorRepoService {
    private final AuthorRepository authorRepository;
    private final ModelMapper mapper;
    @Override
    @Transactional(readOnly = true)
    public AuthorEntity findById(Long id) {
        Optional<AuthorEntity> authorOpt = authorRepository.findById(id);

        return authorOpt.orElseThrow(() -> new RecordNotFoundException("Автор не найден. id: " + id));
    }

    @Override
    @Transactional
    public Author saveAuthor(AuthorRecord author) {
        AuthorEntity authorEntity = mapper.map(author, AuthorEntity.class);
        return mapper.map(authorRepository.save(authorEntity), Author.class);
    }

    @Override
    @Transactional
    public Author updateAuthor(AuthorEntity authorEntity) {
        return mapper.map(authorRepository.saveAndFlush(authorEntity), Author.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuthorEntity> findByIds(Iterable<Long> ids){
        return authorRepository.findAllById(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuthorEntity> findAll() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
