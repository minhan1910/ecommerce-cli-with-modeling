package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class QueryMethodTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findByNameMethod() {
        Product product = productRepository.findByName("Product 2");
        System.out.println(product);
    }

    @Test
    void findByNameAndDescriptionMethod() {
        List<Product> product = productRepository.findByNameOrDescription("Product 2", "Product 1 desc");
        System.out.println(product);
    }

    @Test
    void findByDistinctByImageUrlMethod() {
        List<String> products = productRepository.findDistinctImageUrl();
        products.forEach(System.out::println);
    }

    @Test
    void findByPriceGreaterThanMethod() {
        List<Product> byPriceGreaterThan = productRepository.findByPriceGreaterThan(BigDecimal.valueOf(999));
        System.out.println(byPriceGreaterThan);
    }

    @Test
    void findByDateCreatedBetweenMethod() {
        LocalDateTime startDate = LocalDateTime.of(2024, 4, 8, 15, 10, 10);
        LocalDateTime endDate = LocalDateTime.of(2024, 4, 8, 22, 10, 10);

        List<Product> products = productRepository.findByDateCreatedBetween(startDate, endDate);
        products.forEach(System.out::println);
    }

//    @Test
//    void findFirst2ByOrderByNameAscMethod(){
//        System.out.println(productRepository.findFirst2ByOrderByNameAsc());
//    }

    @Test
    void findByNameOrDescriptionJPQLNamedParamMethod() {
        List<Product> byNameOrDescriptionJPQLNamedParam = productRepository.findByNameOrDescriptionJPQLNamedParam("Product 100", "Product 110 desc");
        System.out.println(byNameOrDescriptionJPQLNamedParam);
    }

}
