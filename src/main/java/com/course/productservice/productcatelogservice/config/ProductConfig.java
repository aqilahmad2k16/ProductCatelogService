package com.course.productservice.productcatelogservice.config;

import com.course.productservice.productcatelogservice.dtos.DBExceptionResponseDto;
import com.course.productservice.productcatelogservice.dtos.ExceptionResponseDto;
import com.course.productservice.productcatelogservice.model.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {
    @Bean
    public Product getProductInstance(){
        return new Product();
    }

    @Bean
    public ExceptionResponseDto getExceptionResponseDtoInstance() {
        return new ExceptionResponseDto();
    }

    @Bean
    public DBExceptionResponseDto getDBExcepitonResponseDtoInstance(){
        return new DBExceptionResponseDto();
    }
}
