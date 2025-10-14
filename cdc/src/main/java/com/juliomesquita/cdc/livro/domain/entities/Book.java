package com.juliomesquita.cdc.livro.domain.entities;

import com.juliomesquita.cdc.shared.entities.BaseEntityWithGeneratedId;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "books")
public class Book extends BaseEntityWithGeneratedId {

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "abstract_text", length = 500, nullable = false)
    private String abstractText;

    @Column(name = "summary", columnDefinition = "TEXT")
    private String summary;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "number_of_pages", nullable = false)
    private Integer numberOfPages;

    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
}