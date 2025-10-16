package com.juliomesquita.cdc.book.domain.repositories;

import com.juliomesquita.cdc.book.domain.entities.Category;
import com.juliomesquita.cdc.shared.repositories.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends GenericRepository<Category> {
}
