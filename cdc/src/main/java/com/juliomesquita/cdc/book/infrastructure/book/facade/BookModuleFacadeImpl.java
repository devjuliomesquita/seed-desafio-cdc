package com.juliomesquita.cdc.book.infrastructure.book.facade;

import com.juliomesquita.cdc.book.domain.repositories.dtos.BookPriceInfo;
import com.juliomesquita.cdc.book.domain.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
record BookModuleFacadeImpl(BookRepository bookRepository) implements BookModuleFacade {

    @Override
    public long countExistingBooks(final Collection<UUID> ids) {
        if (ids == null || ids.isEmpty()) {
            return 0;
        }
        return this.bookRepository.countByIdIn(ids);
    }

    @Override
    public List<BookPriceInfo> findBookPrices(final Collection<UUID> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyList();
        }
        return this.bookRepository.findPriceInfoByIdIn(ids);
    }
}
