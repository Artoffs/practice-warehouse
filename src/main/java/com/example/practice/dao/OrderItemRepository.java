package com.example.practice.dao;

import com.example.practice.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("from OrderItem ot join fetch ot.order join fetch ot.product p join fetch p.supplier")
    List<OrderItem> getAllWithDeps();
}
