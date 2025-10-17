package com.juliomesquita.cdc.shared.services;

public interface GenericMapperUp<E> {
    E toDomain(E entity);
}
