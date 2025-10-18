package com.juliomesquita.cdc.order.domain.repositories;

import com.juliomesquita.cdc.order.domain.entities.Order;
import com.juliomesquita.cdc.shared.repositories.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends GenericRepository<Order> {

}
