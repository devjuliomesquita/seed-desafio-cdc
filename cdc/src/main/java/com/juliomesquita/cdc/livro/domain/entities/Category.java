package com.juliomesquita.cdc.livro.domain.entities;

import com.juliomesquita.cdc.shared.entities.BaseEntityWithGeneratedId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "categories")
public class Category extends BaseEntityWithGeneratedId {

    @NotBlank
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Deprecated
    public Category() {
    }

    public Category(@NotBlank String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}