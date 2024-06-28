package com.course.productservice.productcatelogservice.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Product extends BaseModel{
    private String title;
    private double price;
    private String description;
    private String image;
    private int qty;
    private int numberOfOrders;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn
    private Category category;
}
