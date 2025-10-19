package com.juliomesquita.cdc.shared.controllers.doc;

import com.juliomesquita.cdc.shared.controllers.FilterRequest;
import com.juliomesquita.cdc.shared.utils.DefaultPublicAPIResponses;
import com.juliomesquita.cdc.shared.utils.Pagination;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

public interface GenericDoc<CREQ, UREQ, RESP> {

    @Operation(summary = "Create a new resource", operationId = "create", description = "This endpoint receives the necessary parameters for creating a resource.")
    @ApiResponse(responseCode = "201", description = "Resource created successfully")
    @DefaultPublicAPIResponses
    ResponseEntity<RESP> create(@RequestBody CREQ request);

    @Operation(summary = "Find a resource by its ID", operationId = "findById", description = "This endpoint receives the necessary parameters for find of the resource.")
    @ApiResponse(responseCode = "200", description = "Resource found")
    @DefaultPublicAPIResponses
    ResponseEntity<RESP> findById(@PathVariable UUID id);

    @Operation(summary = "List all resources with pagination and search", operationId = "findAll", description = "This endpoint receives the necessary parameters for find an list of the resources.")
    @ApiResponse(responseCode = "200", description = "Resources listed successfully")
    @DefaultPublicAPIResponses
    ResponseEntity<Pagination<RESP>> findAll(
        @RequestParam(value = "page") @DefaultValue("1") int page,
        @RequestParam(value = "size") @DefaultValue("10") int size,
        @RequestParam(value = "sort", required = false) @DefaultValue("id") String sort,
        @RequestParam(value = "direction", required = false) @DefaultValue("asc") String direction,
        @RequestBody FilterRequest filters
        );

    @Operation(summary = "Update an existing resource by its ID", operationId = "update", description = "This endpoint receives the necessary parameters for updating a resource.")
    @ApiResponse(responseCode = "200", description = "Resource updated successfully")
    @DefaultPublicAPIResponses
    ResponseEntity<RESP> update(@PathVariable UUID id, @RequestBody UREQ request);

    @Operation(summary = "Delete a resource by its ID", operationId = "delete", description = "This endpoint receives the necessary parameters for deleting a resource.")
    @ApiResponse(responseCode = "204", description = "Resource deleted successfully")
    @DefaultPublicAPIResponses
    ResponseEntity<Void> delete(@PathVariable UUID id);
}
