package com.example.practice.dao;

import com.example.practice.dto.OrderItemProjection;
import com.example.practice.entity.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("""
                Select new com.example.practice.dto.OrderItemProjection(
                                o.id,
                                p.id,
                                oi.quantity,
                                oi.priceAtShipment) 
                FROM OrderItem oi 
                                JOIN oi.order o
                                JOIN oi.product p           
                """)
    Page<OrderItemProjection> getAllWithDeps(Pageable pageable);
}
