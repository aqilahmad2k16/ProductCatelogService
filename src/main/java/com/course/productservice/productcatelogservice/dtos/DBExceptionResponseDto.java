package com.course.productservice.productcatelogservice.dtos;

import lombok.Data;

@Data
public class DBExceptionResponseDto {
    private String errorMessage;
    private int errorCode;
}
