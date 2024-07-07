package com.course.productservice.productcatelogservice.service.productserviceImplementation;

import com.course.productservice.productcatelogservice.exceptions.ErrorMessageConstants;
import com.course.productservice.productcatelogservice.exceptions.ProductIsAlreadyExistException;
import com.course.productservice.productcatelogservice.exceptions.ProductNotFoundException;
import com.course.productservice.productcatelogservice.model.Product;
import com.course.productservice.productcatelogservice.repository.ProductRepository;
import com.course.productservice.productcatelogservice.service.ProductService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService {
    private ProductRepository productRepository;
    private RedisTemplate redisTemplate;

    public ProductServiceImp(ProductRepository productRepository, RedisTemplate redisTemplate){
        this.productRepository = productRepository;
        this.redisTemplate = redisTemplate;
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
        //first find product in the cache (redis)
        Product cacheProduct = (Product) redisTemplate.opsForHash().get("PRODUCTS", "PRODUCT_" + id);
        if(cacheProduct != null){
            //cache hit
            return cacheProduct;
        }
        //cache mis
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
            // throw exception product does not exist;
            throw new ProductNotFoundException(ErrorMessageConstants.PRODUCT_NOT_FOUND, id);
        }

        Product savedProduct = productOptional.get();
        //store data in redis
        /*
        Map Name => PRODUCTS (key)
        id => hashKey
        value => product object
        * */
         redisTemplate.opsForHash().put("PRODUCTS", "PRODUCT_" + id, savedProduct);
        return savedProduct;
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }
}
