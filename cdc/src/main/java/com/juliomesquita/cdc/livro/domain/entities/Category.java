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

}