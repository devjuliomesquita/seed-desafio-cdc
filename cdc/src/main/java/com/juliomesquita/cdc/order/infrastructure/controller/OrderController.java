package com.juliomesquita.cdc.order.infrastructure.controller;

import com.juliomesquita.cdc.order.application.crud.dtos.OrderCreateRequest;
import com.juliomesquita.cdc.order.application.crud.dtos.OrderResponse;
import com.juliomesquita.cdc.order.application.crud.dtos.OrderUpdateRequest;
import com.juliomesquita.cdc.order.application.crud.service.OrderService;
import com.juliomesquita.cdc.order.application.usecases.FinalizedUseCase;
import com.juliomesquita.cdc.order.domain.entities.Order;
import com.juliomesquita.cdc.order.infrastructure.documentation.OrderDoc;
import com.juliomesquita.cdc.shared.controllers.GenericController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static com.juliomesquita.cdc.order.infrastructure.documentation.OrderMetadata.searchableFields;
import static com.juliomesquita.cdc.order.infrastructure.documentation.OrderMetadata.searchableRelations;


@RestController
@RequestMapping("/orders")
@Tag(name = "Orders", description = "API for management of orders.")
public class OrderController extends GenericController<Order, OrderCreateRequest, OrderUpdateRequest, OrderResponse, OrderService> implements OrderDoc {
    private final FinalizedUseCase finalizedUseCase;

    public OrderController(final OrderService service, final FinalizedUseCase finalizedUseCase) {
        super(service);
        this.finalizedUseCase = Objects.requireNonNull(finalizedUseCase, "finalizedUseCase cannot be null");
    }

    @Override
    public ResponseEntity<UUID> finalizedOrder(final UUID orderId) {
        final UUID response = this.finalizedUseCase.execute(orderId);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getSearchMetadataEndpoint() {
        return ResponseEntity.ok(Map.of(
            "searchableFields", searchableFields(),
            "searchableRelations", searchableRelations()
        ));
    }
}
