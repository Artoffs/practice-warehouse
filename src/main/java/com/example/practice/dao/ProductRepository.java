package com.example.practice.dao;

import com.example.practice.dto.product.ProductProjection;
import com.example.practice.entity.Product;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("""
        SELECT new com.example.practice.dto.product.ProductProjection (
                s.name, p.name, p.description, p.price
                )
        FROM Product p
        JOIN p.supplier s
        """)
    Page<ProductProjection> findAllProjections(@NonNull Pageable pageable);
}
