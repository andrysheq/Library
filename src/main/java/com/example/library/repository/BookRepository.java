package com.example.library.repository;

import com.example.library.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends BaseRepository<BookEntity> {

    @Transactional
    BookEntity saveAndFlush(BookEntity bookEntity);

    @Override
    @Transactional(readOnly = true)
    @Query("select b from BookEntity b left join fetch b.authorEntities")
    List<BookEntity> findAll();

    @Override
    @Transactional(readOnly = true)
    @Query("select b from BookEntity b left join fetch b.authorEntities")
    List<BookEntity> findAll(Sort sort);

    @Override
    @Transactional(readOnly = true)
    @Query("select b from BookEntity b left join fetch b.authorEntities where b.id in ?1")
    List<BookEntity> findAllById(Iterable<Long> iterable);

    @Override
    @Transactional(readOnly = true)
    @Query("select b from BookEntity b left join fetch b.authorEntities")
    Page<BookEntity> findAll(Pageable pageable);

    @Override
    @Transactional(readOnly = true)
    @Query("select b from BookEntity b left join fetch b.authorEntities where b.id = ?1")
    Optional<BookEntity> findById(Long id);
}
