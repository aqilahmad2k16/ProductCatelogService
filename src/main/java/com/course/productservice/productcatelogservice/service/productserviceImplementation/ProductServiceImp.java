package com.course.productservice.productcatelogservice.service.productserviceImplementation;

import com.course.productservice.productcatelogservice.exceptions.ErrorMessageConstants;
import com.course.productservice.productcatelogservice.exceptions.ProductIsAlreadyExistException;
import com.course.productservice.productcatelogservice.exceptions.ProductNotFoundException;
import com.course.productservice.productcatelogservice.model.Product;
import com.course.productservice.productcatelogservice.repository.ProductRepository;
import com.course.productservice.productcatelogservice.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService {
    private ProductRepository productRepository;

    public ProductServiceImp(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Override
    public Product saveProduct(Product product) throws ProductIsAlreadyExistException {
        Optional<Product> obtainedProduct = productRepository.findByTitleAndPrice(product.getTitle(), product.getPrice());
        Product savedProduct;
        if(obtainedProduct.isEmpty()){
            //handle exception
            savedProduct = productRepository.save(product);
            return savedProduct;
        } else {
            Product product1 = obtainedProduct.get();
            if(product1.getTitle().equalsIgnoreCase(product.getTitle()) && product1.getCategory().getTitle().equalsIgnoreCase(product.getCategory().getTitle())){
                throw new ProductIsAlreadyExistException("Product is already exist", product1.getId());
            }
        }

        return null;
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
            // throw exception product does not exist;
            throw new ProductNotFoundException(ErrorMessageConstants.PRODUCT_NOT_FOUND, id);
        }
        return productOptional.get();
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }
}
