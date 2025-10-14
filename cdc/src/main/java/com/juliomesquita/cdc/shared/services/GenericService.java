package com.juliomesquita.cdc.shared.services;

import com.juliomesquita.cdc.shared.entities.BaseEntityWithGeneratedId;
import com.juliomesquita.cdc.shared.exceptions.ResourceNotFoundException;
import com.juliomesquita.cdc.shared.repositories.GenericRepository;
import com.juliomesquita.cdc.shared.repositories.SpecificationUtils;
import com.juliomesquita.cdc.shared.utils.Pagination;
import com.juliomesquita.cdc.shared.utils.SearchQuery;
import com.juliomesquita.cdc.shared.utils.SearchQueryUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public abstract class GenericService<
    E extends BaseEntityWithGeneratedId,
    REQ,
    RESP,
    R extends GenericRepository<E>,
    M extends GenericMapper<E, REQ, RESP>
    > {

    protected final R repository;
    protected final M mapper;

    protected GenericService(R repository, M mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional("jpaTransactionManager")
    public RESP create(final REQ request) {
        E entity = mapper.toEntity(request);
        E savedEntity = repository.save(entity);
        return mapper.toResponse(savedEntity);
    }

    public RESP findById(final UUID id) {
        E entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + id));
        return mapper.toResponse(entity);
    }

    public Pagination<RESP> findAll(final SearchQuery searchQuery) {
        final PageRequest pageRequest = searchQuery.toPageRequest();

        final Specification<E> specification = SpecificationUtils.build(searchQuery);

        final Page<E> pageable = this.repository.findAll(specification, pageRequest);
        return Pagination.create(
            pageable.map(this.mapper::toResponse).toList(),
            pageable.getNumber(),
            pageable.getSize(),
            pageable.getTotalElements()
        );
    }

    @Transactional("jpaTransactionManager")
    public RESP update(final UUID id, final REQ request) {
        E entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + id));

        mapper.updateEntityFromRequest(request, entity);
        E savedEntity = repository.save(entity);
        return mapper.toResponse(savedEntity);
    }

    @Transactional("jpaTransactionManager")
    public void delete(final UUID id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Resource not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
