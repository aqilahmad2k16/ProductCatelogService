package com.course.productservice.productcatelogservice.controller;

import com.course.productservice.productcatelogservice.dtos.ProductRequestDto;
import com.course.productservice.productcatelogservice.exceptions.ProductIsAlreadyExistException;
import com.course.productservice.productcatelogservice.model.Product;
import com.course.productservice.productcatelogservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping("/product")
    public ResponseEntity<Product> saveProduct(@RequestBody ProductRequestDto productRequestDto) throws ProductIsAlreadyExistException {
        Product product = productService.saveProduct(ProductRequestDto.from(productRequestDto));
        return new ResponseEntity<>(product, HttpStatus.ACCEPTED);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     * Update api
     */
    @PutMapping("/product/{id}")
    public Product replaceProduct(@RequestBody ProductRequestDto productRequestDto){
        return null;
    }

    /*
    PatchApi
    * */
    @PatchMapping("/product/{id}")
    public Product patialUpdateProduct(@RequestBody ProductRequestDto productRequestDto){
        return null;
    }

    /**
     * Delete api by id
     */
    @DeleteMapping("/product/{id}")
    public String removeProductById(@PathVariable Long id){
        return null;
    }
}
