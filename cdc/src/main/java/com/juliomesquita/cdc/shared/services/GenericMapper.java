package com.juliomesquita.cdc.shared.services;

public interface GenericMapper<E, CREQ, UREQ, RESP> {
    E toEntity(CREQ request);
    RESP toResponse(E entity);
    E updateEntityFromRequest(UREQ request, E entity);
}
