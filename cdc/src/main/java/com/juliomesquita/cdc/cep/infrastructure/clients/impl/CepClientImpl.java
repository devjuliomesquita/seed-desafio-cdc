package com.juliomesquita.cdc.cep.infrastructure.clients.impl;

import com.juliomesquita.cdc.cep.infrastructure.clients.CepClient;
import com.juliomesquita.cdc.cep.infrastructure.clients.dtos.CepResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public record CepClientImpl(
    RestClient restClient
) implements CepClient {

    public static final String CEP_URL_BASE = "https://viacep.com.br/ws/{cep}/json/";

    @Override
    public CepResponse findInfosByCep(final String cep) {
        return this.restClient
            .get()
            .uri(this.formatUrl(cep))
            .headers(headers -> {
                headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            })
            .retrieve()
            .toEntity(CepResponse.class)
            .getBody();
    }

    private String formatUrl(final String cep) {
        return CEP_URL_BASE.replace("{cep}", cep);
    }
}
