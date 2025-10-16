package com.juliomesquita.cdc.book.infrastructure.book.documentation;

import com.juliomesquita.cdc.book.application.book.usecases.findpartial.BookPartialResponse;
import com.juliomesquita.cdc.shared.utils.DefaultPublicAPIResponses;
import com.juliomesquita.cdc.shared.utils.Pagination;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface BookDoc {
    @Operation(summary = "List all parcial resources with pagination and search", operationId = "findAllPartial", description = "This endpoint receives the necessary parameters for find an list of the resources.")
    @ApiResponse(responseCode = "200", description = "Resources listed successfully")
    @DefaultPublicAPIResponses
    @GetMapping("/partial")
    ResponseEntity<Pagination<BookPartialResponse>> findAllPartial(
        @RequestParam(value = "page") @DefaultValue("1") int page,
        @RequestParam(value = "size") @DefaultValue("10") int size,
        @RequestParam("terms") String terms,
        @RequestParam(value = "sort", required = false) @DefaultValue("id") String sort,
        @RequestParam(value = "direction", required = false) @DefaultValue("asc") String direction
    );
}
