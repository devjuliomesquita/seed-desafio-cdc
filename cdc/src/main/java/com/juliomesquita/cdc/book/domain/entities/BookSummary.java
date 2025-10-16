package com.juliomesquita.cdc.book.domain.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document("book_summaries")
public class BookSummary {

    @Id
    private UUID id;

    private String summary;

    public static BookSummary create(UUID id, String summary) {
        return new BookSummary(id, summary);
    }

    private BookSummary() {}

    private BookSummary(UUID id, String summary) {
        this.id = id;
        this.summary = summary;
    }

    public UUID getId() {
        return id;
    }

    public String getSummary() {
        return summary;
    }
}
