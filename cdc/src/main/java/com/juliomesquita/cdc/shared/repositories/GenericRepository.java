package com.juliomesquita.cdc.shared.repositories;

import com.juliomesquita.cdc.shared.entities.BaseEntityWithGeneratedId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface GenericRepository<T extends BaseEntityWithGeneratedId> extends JpaRepository<T, UUID>, JpaSpecificationExecutor<T> {
}
