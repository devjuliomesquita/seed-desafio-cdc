package com.juliomesquita.cdc.shared.services;

public interface GenericMapper<E, REQ, RESP> {
    E toEntity(REQ request);
    RESP toResponse(E entity);
    void updateEntityFromRequest(REQ request, E entity);
}
