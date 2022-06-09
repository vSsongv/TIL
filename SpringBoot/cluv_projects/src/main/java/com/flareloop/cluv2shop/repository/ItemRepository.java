package com.flareloop.cluv2shop.repository;

import com.flareloop.cluv2shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ItemRepository extends JpaRepository<Item, Long> {
}
