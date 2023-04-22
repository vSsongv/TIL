package com.flareloop.cluv2shop.repository;

import com.flareloop.cluv2shop.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}