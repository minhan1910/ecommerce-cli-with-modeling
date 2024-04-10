package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveMethod() {
        // create method
        Product product = Product
                .builder()
                .name("Product 4aa")
                .description("Product 1 desc")
                .sku("100ABCDEDAAA")
                .active(true)
                .imageUrl("product1.png")
                .price(new BigDecimal(100))
                .build();
        // save product
        Product productSaved = productRepository.save(product);

        // display product
        System.out.println(productSaved);
    }

    @Test
    public void updateUsingSaveMethod() {
        Long id = 1l;
        Product product = productRepository.findById(id).get();

        product.setActive(false);
        product.setName("update product 1");

        Product productUpdated = productRepository.save(product);

        System.out.println(productUpdated);
    }

    @Test
    public void findByIdMethod() {
        Long id = 3L;
        Product product = productRepository.findById(id).get();

        System.out.println(product);
    }

    @Test
    public void saveAllMethod() {
        // create method
        Product product1 = Product
                .builder()
                .name("Product 100")
                .description("Product 100 desc")
                .sku("11AA")
                .active(true)
                .imageUrl("product100.png")
                .price(new BigDecimal(100))
                .build();

        Product product2 = Product
                .builder()
                .name("Product 110")
                .description("Product 110 desc")
                .sku("111AA")
                .active(true)
                .imageUrl("product110.png")
                .price(new BigDecimal(100))
                .build();

        Product product3 = Product
                .builder()
                .name("Product 111")
                .description("Product 111 desc")
                .sku("1111AA")
                .active(true)
                .imageUrl("product111.png")
                .price(new BigDecimal(100))
                .build();

        List<Product> products = productRepository.saveAll(List.of(product1, product2, product3));
        System.out.println(products);
    }

    @Test
    public void findAllMethod() {
        List<Product> all = productRepository.findAll();
        System.out.println(all);
    }

    @Test
    public void deleteByIdMethod() {
        productRepository.deleteById(1L);
    }

    @Test
    public void deleteMethod() {
        Optional<Product> product = productRepository.findById(2L);

        product.ifPresent(productRepository::delete);
    }

    @Test
    public void existsByIdMethod() {
        boolean b = productRepository.existsById(2L);
        System.out.println(b);
    }
}