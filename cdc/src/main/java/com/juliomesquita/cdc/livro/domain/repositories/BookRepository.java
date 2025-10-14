package com.juliomesquita.cdc.livro.domain.repositories;

import com.juliomesquita.cdc.livro.domain.entities.Book;
import com.juliomesquita.cdc.shared.repositories.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends GenericRepository<Book> {
}
