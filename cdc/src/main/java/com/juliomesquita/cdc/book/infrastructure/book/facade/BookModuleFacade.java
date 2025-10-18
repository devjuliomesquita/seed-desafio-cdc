package com.juliomesquita.cdc.book.infrastructure.book.facade;

import com.juliomesquita.cdc.book.domain.repositories.dtos.BookPriceInfo;
import org.springframework.modulith.NamedInterface;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@NamedInterface
public interface BookModuleFacade {
    long countExistingBooks(final Collection<UUID> ids);
    List<BookPriceInfo> findBookPrices(final Collection<UUID> ids);
}
