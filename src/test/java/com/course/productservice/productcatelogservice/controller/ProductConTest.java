package com.course.productservice.productcatelogservice.controller;

import com.course.productservice.productcatelogservice.config.ProductConfig;
import com.course.productservice.productcatelogservice.controller.ProductController;
import com.course.productservice.productcatelogservice.exceptions.ProductIsAlreadyExistException;
import com.course.productservice.productcatelogservice.model.Category;
import com.course.productservice.productcatelogservice.model.Product;
import com.course.productservice.productcatelogservice.repository.ProductRepository;
import com.course.productservice.productcatelogservice.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductConTest {
    @Autowired
    private ProductController productController;
    @Autowired
    private ProductConfig config;

//    @MockBean
    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;



    @Test
    public void testGetProductById(){
        Product actualProduct = config.getProductInstance();

        actualProduct.setTitle("Mens Cotton Jacket");
        actualProduct.setId(3L);
        actualProduct.setPrice(55.99);
        when(productService.getProductById(3L)).
                thenReturn(actualProduct); //Add mock bean for productService object (means make it mock object)

        ResponseEntity<Product> responseProduct = productController.getProductById(3L);
        Product product = responseProduct.getBody();
        assertNotNull(product);
        assertEquals(actualProduct.getTitle(), product.getTitle());
        assertEquals(actualProduct.getPrice(), product.getPrice());
    }

    //test for exception if thrown
    @Test
    public void testGetProductByIdNotFound(){
        Product actualProduct = config.getProductInstance();
        actualProduct.setTitle("Mens Cotton Jacket");
        actualProduct.setId(3L);
        actualProduct.setPrice(55.99);
        Category category = new Category();
        category.setId(3L);
        category.setTitle("men's clothing");
        actualProduct.setCategory(category);

        when(productRepository.findByTitleAndPrice(actualProduct.getTitle(), actualProduct.getPrice())).
                thenReturn(Optional.of(actualProduct));

        ProductIsAlreadyExistException exception = assertThrows(ProductIsAlreadyExistException.class, () -> {
            productService.saveProduct(actualProduct);
        });

//        assertEquals("Product is already", exception.getMessage());

        /*
        //Test failed
        Expected :Product is already
        Actual   :Product is already exist
        * */

        //Test passed
        assertEquals("Product is alredy exist", exception.getMessage());
    }

}
