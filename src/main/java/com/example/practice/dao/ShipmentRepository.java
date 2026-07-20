package com.example.practice.dao;

import com.example.practice.dto.shipment.ShipmentProjection;
import com.example.practice.entity.Shipment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    @Query("""
            SELECT new com.example.practice.dto.shipment.ShipmentProjection(
                        s.id, pick.address, s.status, s.createdAt
                        )
                        FROM Shipment s
                        JOIN s.pickUpPoint pick
            """)
    Page<ShipmentProjection> findAllProjections(Pageable pageable);
}
