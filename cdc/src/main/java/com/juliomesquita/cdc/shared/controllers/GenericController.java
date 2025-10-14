package com.juliomesquita.cdc.shared.controllers;

import com.juliomesquita.cdc.shared.entities.BaseEntityWithGeneratedId;
import com.juliomesquita.cdc.shared.services.GenericService;
import com.juliomesquita.cdc.shared.utils.Pagination;
import com.juliomesquita.cdc.shared.utils.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public abstract class GenericController<
        E extends BaseEntityWithGeneratedId,
        REQ,
        RESP,
        S extends GenericService<E, REQ, RESP, ?, ?>> {

    protected final S service;

    protected GenericController(S service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RESP> create(@RequestBody REQ request) {
        return ResponseEntity.status(201).body(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RESP> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<Pagination<RESP>> findAll(SearchQuery searchQuery) {
        return ResponseEntity.ok(service.findAll(searchQuery));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RESP> update(@PathVariable UUID id, @RequestBody REQ request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
