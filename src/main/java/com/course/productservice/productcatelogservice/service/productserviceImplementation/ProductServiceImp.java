package com.course.productservice.productcatelogservice.service.productserviceImplementation;

import com.course.productservice.productcatelogservice.exceptions.ProductIsAlreadyExistException;
import com.course.productservice.productcatelogservice.model.Product;
import com.course.productservice.productcatelogservice.repository.ProductRepository;
import com.course.productservice.productcatelogservice.service.ProductService;
import org.springframework.stereotype.Service;

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

        if(obtainedProduct.isEmpty()){
            //handle exception
            return null;
        }

        Product product1 = obtainedProduct.get();
        if(product1.getTitle().equalsIgnoreCase(product.getTitle()) && product1.getCategory().getTitle().equalsIgnoreCase(product.getCategory().getTitle())){
            throw new ProductIsAlreadyExistException("Product is already exist", product1.getId());
        }

        Optional<Product> productOptional = Optional.of(productRepository.save(product));
        return productOptional.get();
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
            // throw exception product does not exist;
            return null;
        }
        return productOptional.get();
    }
}
