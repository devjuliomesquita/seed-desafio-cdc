package com.juliomesquita.cdc.book.domain.repositories;

import com.juliomesquita.cdc.book.domain.entities.BookSummary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookSummaryRepository extends MongoRepository<BookSummary, UUID> {
}
