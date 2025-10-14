package com.juliomesquita.cdc.livro.domain.repositories;

import com.juliomesquita.cdc.livro.domain.entities.Category;
import com.juliomesquita.cdc.shared.repositories.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends GenericRepository<Category> {
}
