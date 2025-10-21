package com.juliomesquita.cdc.order.application.usecases;

import com.juliomesquita.cdc.order.domain.entities.Order;
import com.juliomesquita.cdc.order.domain.repositories.OrderRepository;
import com.juliomesquita.cdc.shared.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
class FinalizedUseCaseImpl extends FinalizedUseCase {
    private final OrderRepository orderRepository;

    public FinalizedUseCaseImpl(final OrderRepository orderRepository) {
        this.orderRepository = Objects.requireNonNull(orderRepository, "orderRepository cannot be null");
    }

    @Override
    public UUID execute(final UUID orderId) {
        final Order order = this.orderRepository
            .findById(orderId)
            .orElseThrow(() -> new ResourceNotFoundException("Order not found"))
            .finalizeOrder();

        return this.orderRepository.save(order).getId();
    }
}
