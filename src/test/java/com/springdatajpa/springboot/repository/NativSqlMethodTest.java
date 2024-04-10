package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class NativSqlMethodTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findByNameOrDescriptionSQLIndexParamMethod() {
        List<Product> products = productRepository.findByNameOrDescriptionSQLIndexParam("Product 100", "Product 110 desc");
        System.out.println(products);
    }

    @Test
    void findByNameOrDescriptionSQLNamedParamMethd() {
        List<Product> products = productRepository.findByNameOrDescriptionSQLNamedParam("Product 100", "Product 110 desc");
        System.out.println(products);
    }

}
