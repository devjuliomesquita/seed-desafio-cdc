package com.juliomesquita.cdc.order.infrastructure.controller;

import com.juliomesquita.cdc.order.application.crud.dtos.OrderCreateRequest;
import com.juliomesquita.cdc.order.application.crud.dtos.OrderResponse;
import com.juliomesquita.cdc.order.application.crud.dtos.OrderUpdateRequest;
import com.juliomesquita.cdc.order.application.crud.service.OrderService;
import com.juliomesquita.cdc.order.domain.entities.Order;
import com.juliomesquita.cdc.shared.controllers.GenericController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@Tag(name = "Orders", description = "API for management of orders.")
public class OrderController extends GenericController<Order, OrderCreateRequest, OrderUpdateRequest, OrderResponse, OrderService> {
    public OrderController(final OrderService service) {
        super(service);
    }
}
