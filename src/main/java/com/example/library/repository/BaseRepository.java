package com.example.library.repository;

import com.example.library.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e")
    List<T> findAll();

    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e")
    List<T> findAll(Sort sort);

    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.id in ?1")
    List<T> findAllById(Iterable<Long> iterable);

    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e")
    Page<T> findAll(Pageable pageable);

    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.id = ?1")
    Optional<T> findById(Long id);

    @Override
    default boolean existsById(Long id) {
        return findById(id).isPresent();
    }

    @Override
    @Transactional(readOnly = true)
    @Query("select count(e) from #{#entityName} e")
    long count();

}