package com.example.goodfood.repo;

import com.example.goodfood.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepo extends JpaRepository<Product,Long> {

}
