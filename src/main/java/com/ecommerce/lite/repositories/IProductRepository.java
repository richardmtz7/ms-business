package com.ecommerce.lite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.lite.entities.Products;

@Repository
public interface IProductRepository extends JpaRepository<Products, Integer> {

}
