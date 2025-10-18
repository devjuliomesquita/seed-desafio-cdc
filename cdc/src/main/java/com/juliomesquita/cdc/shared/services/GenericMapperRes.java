package com.juliomesquita.cdc.shared.services;

public interface GenericMapperRes<E, RESP> {
    RESP toResponse(E entity);
}
