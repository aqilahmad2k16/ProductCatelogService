package com.course.productservice.productcatelogservice.dtos;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ExceptionResponseDto {
    private String errorMessage;
    private int errorCode;
    private Long id;
}
