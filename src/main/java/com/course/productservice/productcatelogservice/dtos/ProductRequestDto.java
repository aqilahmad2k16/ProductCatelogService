package com.course.productservice.productcatelogservice.dtos;

import com.course.productservice.productcatelogservice.config.ProductConfig;
import com.course.productservice.productcatelogservice.model.Category;
import com.course.productservice.productcatelogservice.model.Product;
import lombok.Data;

@Data
public class ProductRequestDto {
    private Long id;
    private String title;
    private double price;
    private String image;
    private String description;
    private int qty;
    private int numberOfOrders;
    private Category category;

    public static Product from(ProductRequestDto productRequestDto, ProductConfig productConfig) {
        Product product = productConfig.getProductInstance();
        product.setTitle(productRequestDto.getTitle());
        product.setPrice(productRequestDto.getPrice());
        product.setImage(productRequestDto.getImage());
        product.setQty(productRequestDto.getQty());
        product.setDescription(productRequestDto.getDescription());
        product.setNumberOfOrders(productRequestDto.getNumberOfOrders());
        Category category1 = new Category();
        category1.setTitle(productRequestDto.getCategory().getTitle());
        product.setCategory(category1);
        return product;
    }
}
