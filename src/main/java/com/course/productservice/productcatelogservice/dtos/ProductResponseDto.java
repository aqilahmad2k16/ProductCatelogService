package com.course.productservice.productcatelogservice.dtos;

import com.course.productservice.productcatelogservice.model.Category;
import lombok.Data;

@Data
public class ProductResponseDto {
    private Long id;
    private String title;
    private double price;
    private String image;
    private int qty;
    private int numberOfOrders;
    private Category category;

}
