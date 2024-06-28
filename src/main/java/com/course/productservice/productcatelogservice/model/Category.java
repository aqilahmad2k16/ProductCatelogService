package com.course.productservice.productcatelogservice.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Category extends BaseModel{
    private String title;
}
