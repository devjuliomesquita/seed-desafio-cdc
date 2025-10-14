package com.juliomesquita.cdc.livro.domain.entities;

import com.juliomesquita.cdc.shared.entities.BaseEntityWithGeneratedId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Category extends BaseEntityWithGeneratedId {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public static Category create(final String name) {
        return new Category(name);
    }

    public Category update(final String name) {
        this.name = name;
        return this;
    }

    private Category(final String name) {
        this.name = name;
    }

    protected Category() {
    }

    public String getName() {
        return name;
    }
}