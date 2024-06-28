package com.course.productservice.productcatelogservice.exceptions;
import lombok.Data;

@Data
public class ProductIsAlreadyExistException extends Exception{
    private Long id;
    public ProductIsAlreadyExistException(String message, Long id){
        super(message);
        this.id = id;
    }
}
