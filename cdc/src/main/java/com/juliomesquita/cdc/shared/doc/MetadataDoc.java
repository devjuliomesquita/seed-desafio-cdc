package com.juliomesquita.cdc.shared.doc;

import com.juliomesquita.cdc.shared.utils.DefaultPublicAPIResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

public interface MetadataDoc {
    @Operation(summary = "Get Search Metadata", operationId = "getSearchMetadataEndpoint", description = "Provides the fields and relationships available to filter the list of resources.")
    @ApiResponse(responseCode = "200", description = "Resources listed successfully")
    @DefaultPublicAPIResponses
    @GetMapping("/search-metadata")
    ResponseEntity<Map<String, Object>> getSearchMetadataEndpoint();
}
