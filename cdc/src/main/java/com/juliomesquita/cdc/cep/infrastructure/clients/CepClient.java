package com.juliomesquita.cdc.cep.infrastructure.clients;

import com.juliomesquita.cdc.cep.infrastructure.clients.dtos.CepResponse;

public interface CepClient {
    CepResponse findInfosByCep(String cep);
}
