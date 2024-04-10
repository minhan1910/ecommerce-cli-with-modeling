package com.springdatajpa.springboot.service;

import com.springdatajpa.springboot.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> searchProducts(String query);
}
