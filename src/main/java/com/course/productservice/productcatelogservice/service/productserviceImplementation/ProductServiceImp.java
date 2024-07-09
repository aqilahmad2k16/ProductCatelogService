package com.course.productservice.productcatelogservice.service.productserviceImplementation;

import com.course.productservice.productcatelogservice.exceptions.DBEmptyException;
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
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
            redisTemplate.opsForHash().put("PRODUCTS", "PRODUCT_" + savedProduct.getId(), savedProduct);
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
        Map Name => PRODUCTS (key) This is the key of the Redis hash where the data will be stored. Think of it as the name of the hash table.
        id => hashKey => This is the hash key within the "PRODUCTS" hash. It uniquely identifies the entry within the hash.
        value => product object => This is the value to be associated with the hash key. It represents the product data you want to store.
        * */
         redisTemplate.opsForHash().put("PRODUCTS", "PRODUCT_" + id, savedProduct);
        return savedProduct;
    }

    @Override
    public List<Product> getAllProduct() {
        Map<Object, Object> productMap = redisTemplate.opsForHash().entries("PRODUCTS");
        List<Product> products = productMap.values().stream().
                                            map(value -> (Product) value).
                                            collect(Collectors.toList());
        if(products.isEmpty()){
            throw new DBEmptyException(ErrorMessageConstants.INSUFFICIENT_FUNDS);
        } else {
            return products;
        }
    }

    @Override
    public List<Product> saveAllProductToCache() {
        List<Product> products = productRepository.findAll();
        if(products.isEmpty()){
            //throw ann exceptions
            throw new DBEmptyException(ErrorMessageConstants.RESOURCE_NOT_FOUND);
        }

        products.stream().forEach(product -> redisTemplate.opsForHash().put("PRODUCTS", "PRODUCT_" + product.getId(), product));
        return products;
    }
}
