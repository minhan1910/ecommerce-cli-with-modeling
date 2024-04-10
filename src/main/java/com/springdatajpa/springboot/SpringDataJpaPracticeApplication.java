package com.springdatajpa.springboot;

import com.springdatajpa.springboot.entity.Order;
import com.springdatajpa.springboot.entity.Product;
import com.springdatajpa.springboot.repository.OrderRepository;
import com.springdatajpa.springboot.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringDataJpaPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaPracticeApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ProductService productService) {
		return runner -> {
//			Order order = orderRepository.findById(3L).get();
//			System.out.println(order);

//			List<Product> products = productService.searchProducts("Product 2");
//			products.forEach(System.out::println);
		};
	}
}
