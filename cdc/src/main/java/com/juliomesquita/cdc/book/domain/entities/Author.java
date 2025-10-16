package com.juliomesquita.cdc.book.domain.entities;

import com.juliomesquita.cdc.shared.entities.BaseEntityWithGeneratedId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "authors")
public class Author extends BaseEntityWithGeneratedId {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "description", length = 400, nullable = false)
    private String description;

    public static Author create(final String name, final String email, final String description) {
        return new Author(name, email, description);
    }

    public Author update(final String name, final String email, final String description) {
        this.name = name;
        this.email = email;
        this.description = description;
        return this;
    }

    private Author(final String name, final String email, final String description) {
        this.name = name;
        this.email = email;
        this.description = description;
    }

    protected Author() {

    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }
}