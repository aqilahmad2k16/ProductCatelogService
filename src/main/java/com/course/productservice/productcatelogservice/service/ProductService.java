package com.course.productservice.productcatelogservice.service;

import com.course.productservice.productcatelogservice.dtos.ProductRequestDto;
import com.course.productservice.productcatelogservice.exceptions.ProductIsAlreadyExistException;
import com.course.productservice.productcatelogservice.model.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product) throws ProductIsAlreadyExistException;

    Product getProductById(Long id);

    List<Product> getAllProduct();

    List<Product> saveAllProductToCache();
}
