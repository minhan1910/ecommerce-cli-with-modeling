package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.OrderItem;
import com.springdatajpa.springboot.entity.Product;
import com.springdatajpa.springboot.entity.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class OneToManyBidirectionalMappingsTest {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void get() {
        OrderItem orderItem = orderItemRepository.findById(9L).get();
        System.out.println(orderItem.getOrder());
    }

    @Test
    void createCategory() {
        ProductCategory productCategory = ProductCategory.builder()
                .categoryName("Shirt")
                .categoryDescription("Shirt t")
                .build();

        productCategoryRepository.save(productCategory);
    }

    @Test
    void addCategoryToProduct() {
        Product product = productRepository.findById(3L).get();
        product.setProductCategory(productCategoryRepository.findById(1L).get());
        productRepository.save(product);
    }

    @Test
    @Transactional
    void getProductCategoryWithProducts() {
        ProductCategory productCategory = productCategoryRepository.findById(1L).get();
        System.out.println(productCategory.getProducts());
    }

}
