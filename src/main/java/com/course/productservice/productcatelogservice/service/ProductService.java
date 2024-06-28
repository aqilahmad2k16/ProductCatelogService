package com.course.productservice.productcatelogservice.service;

import com.course.productservice.productcatelogservice.dtos.ProductRequestDto;
import com.course.productservice.productcatelogservice.exceptions.ProductIsAlreadyExistException;
import com.course.productservice.productcatelogservice.model.Product;

public interface ProductService {
    Product saveProduct(Product product) throws ProductIsAlreadyExistException;

    Product getProductById(Long id);
}
