package com.juliomesquita.cdc.cep.infrastructure.controller.documentation;

import com.juliomesquita.cdc.cep.infrastructure.clients.dtos.CepResponse;
import com.juliomesquita.cdc.shared.utils.DefaultPublicAPIResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface CepDoc {
    @Operation(summary = "Search for location information using the provided zip code", operationId = "findInfosByCep", description = "Search for location information using the provided zip code.")
    @ApiResponse(responseCode = "200", description = "Resources listed successfully")
    @DefaultPublicAPIResponses
    @GetMapping("/{cep}")
    ResponseEntity<CepResponse> findInfosByCep(
        @PathVariable(value = "cep") String cep
    );
}
