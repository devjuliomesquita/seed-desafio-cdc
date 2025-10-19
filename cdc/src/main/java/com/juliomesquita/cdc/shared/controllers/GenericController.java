package com.juliomesquita.cdc.shared.controllers;

import com.juliomesquita.cdc.shared.controllers.doc.GenericDoc;
import com.juliomesquita.cdc.shared.entities.BaseEntityWithGeneratedId;
import com.juliomesquita.cdc.shared.services.GenericMapperCr;
import com.juliomesquita.cdc.shared.services.GenericMapperUp;
import com.juliomesquita.cdc.shared.services.GenericService;
import com.juliomesquita.cdc.shared.utils.Filter;
import com.juliomesquita.cdc.shared.utils.Pagination;
import com.juliomesquita.cdc.shared.utils.SearchQuery;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public abstract class GenericController<
    E extends BaseEntityWithGeneratedId,
    CREQ extends GenericMapperCr<E>,
    UREQ extends GenericMapperUp<E>,
    RESP,
    S extends GenericService<E, CREQ, UREQ, RESP, ?, ?>>
    implements GenericDoc<CREQ, UREQ, RESP> {

    protected final S service;

    protected GenericController(S service) {
        this.service = service;
    }

    @Override
    @PostMapping
    public ResponseEntity<RESP> create(@Valid @RequestBody CREQ request) {
        return ResponseEntity.status(201).body(service.create(request));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<RESP> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Override
    @PostMapping("/search")
    public ResponseEntity<Pagination<RESP>> findAll(
        int currentPage, int itemsPerPage, String sort, String direction, @RequestBody FilterRequest filter) {
        final SearchQuery searchQuery = new SearchQuery(currentPage, itemsPerPage, sort, direction, filter.filters());
        return ResponseEntity.ok(service.findAll(searchQuery));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<RESP> update(@PathVariable UUID id, @Valid @RequestBody UREQ request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
