package com.flareloop.cluv2shop.repository;

import com.flareloop.cluv2shop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}