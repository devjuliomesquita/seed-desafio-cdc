package com.juliomesquita.cdc.livro.domain.entities;

import com.juliomesquita.cdc.livro.domain.valueobjects.BookInfo;
import com.juliomesquita.cdc.livro.domain.valueobjects.ISBN;
import com.juliomesquita.cdc.shared.entities.BaseEntityWithGeneratedId;
import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book extends BaseEntityWithGeneratedId {

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "title", column = @Column(name = "title", nullable = false, unique = true)),
        @AttributeOverride(name = "abstractText", column = @Column(name = "abstract_text", length = 500, nullable = false)),
        @AttributeOverride(name = "price", column = @Column(name = "price", nullable = false)),
        @AttributeOverride(name = "numberOfPages", column = @Column(name = "number_of_pages", nullable = false)),
        @AttributeOverride(name = "publicationDate", column = @Column(name = "publication_date"))
    })
    private BookInfo info;

    @Transient
    private String summary;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "isbn", nullable = false, unique = true))
    private ISBN isbn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    public static Book create(final BookInfo info, final ISBN isbn, final Category category, final Author author) {
        return new Book(info, isbn, category, author);
    }

    public Book update(final BookInfo info, final ISBN isbn, final Category category, final Author author) {
        this.info = info;
        this.isbn = isbn;
        this.category = category;
        this.author = author;
        return this;
    }

    protected Book() {
    }

    private Book(final BookInfo info, final ISBN isbn, final Category category, final Author author) {
        this.info = info;
        this.isbn = isbn;
        this.category = category;
        this.author = author;
    }

    public BookInfo getInfo() {
        return info;
    }

    public ISBN getIsbn() {
        return isbn;
    }

    public Category getCategory() {
        return category;
    }

    public Author getAuthor() {
        return author;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
