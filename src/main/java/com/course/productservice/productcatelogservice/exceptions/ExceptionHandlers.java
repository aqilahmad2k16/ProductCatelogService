package com.course.productservice.productcatelogservice.exceptions;

import com.course.productservice.productcatelogservice.config.ProductConfig;
import com.course.productservice.productcatelogservice.dtos.DBExceptionResponseDto;
import com.course.productservice.productcatelogservice.dtos.ExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

    private ProductConfig productConfig;
    public ExceptionHandlers(ProductConfig productConfig){
        this.productConfig = productConfig;
    }
    @ExceptionHandler(ProductIsAlreadyExistException.class)
    public ResponseEntity<ExceptionResponseDto> handleProductIsAlreadyExistException(ProductIsAlreadyExistException e){
        ExceptionResponseDto exceptionResponseDto = productConfig.getExceptionResponseDtoInstance();
        exceptionResponseDto.setErrorMessage(e.getMessage());
        exceptionResponseDto.setErrorCode(HttpStatus.BAD_REQUEST.value());
        exceptionResponseDto.setId(e.getId());
        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleProductNotFoundException(ProductNotFoundException e){
        ExceptionResponseDto exceptionResponseDto = productConfig.getExceptionResponseDtoInstance();
        exceptionResponseDto.setErrorMessage(e.getMessage());
        exceptionResponseDto.setErrorCode(HttpStatus.BAD_REQUEST.value());
        exceptionResponseDto.setId(e.getId());
        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DBEmptyException.class)
    public ResponseEntity<DBExceptionResponseDto> handleDBEmptyException(DBEmptyException e){
        DBExceptionResponseDto responseDto = productConfig.getDBExcepitonResponseDtoInstance();
        responseDto.setErrorMessage(e.getMessage());
        responseDto.setErrorCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);

    }
}
