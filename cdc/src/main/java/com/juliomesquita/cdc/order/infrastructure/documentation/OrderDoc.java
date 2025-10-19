package com.juliomesquita.cdc.order.infrastructure.documentation;

import com.juliomesquita.cdc.shared.utils.DefaultPublicAPIResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

public interface OrderDoc {
    @Operation(summary = "Finalize an order by ID", operationId = "finalizedOrder", description = "This endpoint receives an orderId in the request and completes the purchase.")
    @ApiResponse(responseCode = "200", description = "Resources listed successfully")
    @DefaultPublicAPIResponses
    @GetMapping("/{orderId}/finalized")
    ResponseEntity<UUID> finalizedOrder(@PathVariable(value = "orderId") UUID orderId);
}
