package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);

    List<Product> findByNameOrDescription(String name, String description);

    @Query("SELECT DISTINCT imageUrl FROM Product")
    List<String> findDistinctImageUrl();

    List<Product> findByPriceGreaterThan(BigDecimal price);

    List<Product> findByPriceLessThan(BigDecimal price);

    List<Product> findByPriceBetween(BigDecimal startPrice, BigDecimal endPrice);

    List<Product> findByDateCreatedBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Product> findByNameContaining(String name);

    List<Product> findByNameLike(String name);

    List<Product> findByNameIn(List<String> names);

//    List<Product> findFirst2ByOrderByNameAsc();

    @Query("select p from Product p where p.name = ?1 or p.description = ?2")
    List<Product> findByNameOrDescriptionJPQLIndexParam(String name, String description);

    @Query("select p from Product p where p.name = :name or p.description = :description")
    List<Product> findByNameOrDescriptionJPQLNamedParam(@Param("name") String name,
                                                        @Param("description") String description);

    @Query(value = "select * from products p where p.name =?1 or p.description =?2", nativeQuery = true)
    List<Product> findByNameOrDescriptionSQLIndexParam(String name, String description);

    @Query(value = "select * from products p where p.name = :name or p.description = :description", nativeQuery = true)
    List<Product> findByNameOrDescriptionSQLNamedParam(@Param("name") String name,
                                                       @Param("description") String description);

    @Query(value = "select p from Product p  " +
            "JOIN FETCH p.productCategory pc " +
            "WHERE p.name LIKE CONCAT('%', :query, '%') " +
            "OR p.description LIKE CONCAT('%', :query, '%')")
    List<Product> searchProducts(@Param("query") String query);

    @Query(value = "select * from Product p where " +
            "p.name LIKE CONCAT('%', :query, '%') " +
            "OR p.description LIKE CONCAT('%', :query, '%')", nativeQuery = true)
    List<Product> searchProductsSQL(@Param("query") String query);
}
