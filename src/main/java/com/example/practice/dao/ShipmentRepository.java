package com.example.practice.dao;

import com.example.practice.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    @Query("from Shipment s join fetch s.orders join fetch s.pickUpPoint")
    List<Shipment> findAllWithDependencies();
}
