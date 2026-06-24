package com.example.practice.dao;

import com.example.practice.entity.Product;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @NonNull
    @Query("from Product p join fetch p.supplier")
    Page<Product> findAll(@NonNull Pageable pageable);
}
