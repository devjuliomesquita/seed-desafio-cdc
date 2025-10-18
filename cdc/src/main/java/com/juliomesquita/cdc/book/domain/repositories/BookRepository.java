package com.juliomesquita.cdc.book.domain.repositories;

import com.juliomesquita.cdc.book.domain.repositories.dtos.BookPriceInfo;
import com.juliomesquita.cdc.book.domain.entities.Book;
import com.juliomesquita.cdc.shared.repositories.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends GenericRepository<Book> {
    long countByIdIn(Iterable<UUID> ids);

    @Query("SELECT new com.juliomesquita.cdc.book.application.dto.BookPriceInfo(b.id, b.info.price) FROM Book b WHERE b.id IN :ids")
    List<BookPriceInfo> findPriceInfoByIdIn(@Param("ids") Collection<UUID> ids);
}
