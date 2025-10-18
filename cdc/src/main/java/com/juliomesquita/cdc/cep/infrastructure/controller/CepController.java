package com.juliomesquita.cdc.cep.infrastructure.controller;

import com.juliomesquita.cdc.cep.infrastructure.clients.CepClient;
import com.juliomesquita.cdc.cep.infrastructure.clients.dtos.CepResponse;
import com.juliomesquita.cdc.cep.infrastructure.controller.documentation.CepDoc;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/cep")
@Tag(name = "Ceps", description = "API for management of ceps.")
public class CepController implements CepDoc {
    private final CepClient cepClient;

    public CepController(final CepClient cepClient) {
        this.cepClient = Objects.requireNonNull(cepClient, "cepClient");
    }

    @Override
    public ResponseEntity<CepResponse> findInfosByCep(final String cep) {
        final CepResponse response = this.cepClient.findInfosByCep(cep);
        return ResponseEntity.ok(response);
    }
}

