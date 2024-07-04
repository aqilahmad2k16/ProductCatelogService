package com.course.productservice.productcatelogservice.exceptions;

import lombok.Data;

@Data
public class ProductNotFoundException extends RuntimeException {
    private Long id;
    public ProductNotFoundException(String message, Long id){
        super(message);
        this.id = id;
    }
}
