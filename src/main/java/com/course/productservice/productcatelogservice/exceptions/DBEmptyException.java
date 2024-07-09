package com.course.productservice.productcatelogservice.exceptions;

public class DBEmptyException extends RuntimeException{
    public DBEmptyException(String message){
        super(message);
    }
}
