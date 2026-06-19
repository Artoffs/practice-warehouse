package com.example.practice.dao;

import com.example.practice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    @Query("from Product p join fetch p.supplier")
    List<Product> findAll();
}
