package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
public class PaginationAndSortingTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void pagination() {
        int pageSize = 5,
                pageIndex = 0;

        Pageable pageable = PageRequest.of(pageIndex, pageSize);

        Page<Product> page = productRepository.findAll(pageable);

        List<Product> products = page.getContent();

        products.forEach(System.out::println);

        System.out.println(page.getTotalPages());
        System.out.println(page.getTotalElements());
        System.out.println(page.getSize());
    }

    @Test
    void sorting() {
        String sortBy = "price";
        String sortDir = "asc";

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        List<Product> all = productRepository.findAll(sort);
        all.forEach(System.out::println);
    }

    @Test
    void sortingWithMultipleCriteria() {
        String sortByPrice = "price";
        String sortByName = "name";
        String sortDir = "desc";
        String sortDirName = "asc";

        Sort sortPrice = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortByPrice).ascending() :
                Sort.by(sortByPrice).descending();

        Sort sortName = sortDirName.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortByName).ascending() :
                Sort.by(sortByName).descending();

        Sort sort = sortPrice.and(sortName);

        List<Product> all = productRepository.findAll(sort);
        all.forEach(System.out::println);
    }

    @Test
    void sortingWithMultipleCriteriaAndPaging() {
        String sortByPrice = "price";
        String sortByName = "name";
        String sortDir = "desc";
        String sortDirName = "asc";
        int pageIndex = 0;
        int pageSize = 4;

        Sort sortPrice = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortByPrice).ascending() :
                Sort.by(sortByPrice).descending();

        Sort sortName = sortDirName.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortByName).ascending() :
                Sort.by(sortByName).descending();

        Sort sort = sortPrice.and(sortName);

        Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);

        Page<Product> all = productRepository.findAll(pageable);
        all.getContent().forEach(System.out::println);
    }

}
