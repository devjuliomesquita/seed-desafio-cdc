package com.juliomesquita.cdc.livro.domain.entities;

import com.juliomesquita.cdc.shared.entities.BaseEntityWithGeneratedId;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "books")
public class Book extends BaseEntityWithGeneratedId {

    @NotBlank
    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @NotBlank
    @Size(max = 500)
    @Column(name = "abstract_text", length = 500, nullable = false)
    private String abstractText;

    @Column(name = "summary", columnDefinition = "TEXT")
    private String summary;

    @NotNull
    @Min(20)
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @NotNull
    @Min(100)
    @Column(name = "number_of_pages", nullable = false)
    private Integer numberOfPages;

    @NotBlank
    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;

    @Future
    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @Deprecated
    public Book() {
    }

    public Book(
            @NotBlank String title,
            @NotBlank @Size(max = 500) String abstractText,
            String summary,
            @NotNull @Min(20) BigDecimal price,
            @NotNull @Min(100) Integer numberOfPages,
            @NotBlank String isbn,
            @Future LocalDate publicationDate,
            @NotNull Category category,
            @NotNull Author author
    ) {
        this.title = title;
        this.abstractText = abstractText;
        this.summary = summary;
        this.price = price;
        this.numberOfPages = numberOfPages;
        this.isbn = isbn;
        this.publicationDate = publicationDate;
        this.category = category;
        this.author = author;
    }
}