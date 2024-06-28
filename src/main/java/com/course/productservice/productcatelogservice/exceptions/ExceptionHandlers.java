package com.course.productservice.productcatelogservice.exceptions;

import com.course.productservice.productcatelogservice.dtos.ExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(ProductIsAlreadyExistException.class)
    public ResponseEntity<ExceptionResponseDto> handleProductIsAlreadyExistException(ProductIsAlreadyExistException e){
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto();
        exceptionResponseDto.setErrorMessage(e.getMessage());
        exceptionResponseDto.setErrorCode(HttpStatus.BAD_REQUEST.value());
        exceptionResponseDto.setId(e.getId());
        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.BAD_REQUEST);
    }
}
