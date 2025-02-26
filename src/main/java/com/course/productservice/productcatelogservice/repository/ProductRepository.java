package com.course.productservice.productcatelogservice.repository;

import com.course.productservice.productcatelogservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    Optional<Product> findById(Long id);

    Optional<Product> findByTitleAndPrice(String title, double price);
}
