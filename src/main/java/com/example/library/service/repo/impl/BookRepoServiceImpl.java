package com.example.library.service.repo.impl;

import com.example.library.models.BookEntity;
import com.example.library.repos.BookRepository;
import com.example.library.service.repo.BookRepoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
@Log4j2
public class BookRepoServiceImpl implements BookRepoService {

    private final BookRepository bookRepository;
    private final BaseMapper mapper;

    private static final String SORT_FIELD = "startDate";

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "bookById", key = "#id")
    public BookEntity findById(Long id) {
        Optional<BookEntity> bookOpt = bookRepository.findById(id);

        return bookOpt.orElseThrow(() -> new RecordNotFoundException("Книга не найдена. id: " + id));
    }

    @Override
    @Transactional
    @CachePut(value = "occasionById", key = "#result.id")
    public Occasion saveOccasion(Occasion occasion) {
        OrganizationEntity organizationEntity = organizationRepoService.findByGlobalId(occasion.getOrganizationId());
        OrganizationAddressEntity organizationAddressEntity = organizationAddressRepoService.findByGlobalId(occasion.getOrganizationAddressId());

        OccasionEntity occasionEntity = mapper.map(occasion, OccasionEntity.class);
        occasionEntity.setOrganizationAddress(organizationAddressEntity);
        occasionEntity.setOrganization(organizationEntity);
        occasionEntity.getRegistrations().forEach(registration -> registration.setOccasion(occasionEntity));
        OccasionEntity savedEntity = occasionRepository.save(occasionEntity);

        return mapper.map(savedEntity, Occasion.class);
    }

    @Override
    @Transactional
    @CachePut(value = "occasionById", key = "#occasionEntity.id")
    public Occasion updateOccasion(OccasionEntity occasionEntity) {
        occasionEntity.getRegistrations().forEach(registration -> registration.setOccasion(occasionEntity));
        OccasionEntity savedEntity = occasionRepository.saveAndFlush(occasionEntity);

        return mapper.map(savedEntity, Occasion.class);
    }

}
