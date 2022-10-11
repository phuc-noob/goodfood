package com.example.goodfood.repo;

import com.example.goodfood.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepo extends JpaRepository<Product,Long> {
    @Query("select * from product limit :page, :size;")
    List<Product> getProducts(@Param("page") int page, @Param("size") int size);
}
