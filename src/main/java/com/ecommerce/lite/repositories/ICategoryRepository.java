package com.ecommerce.lite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.lite.entities.Categories;

@Repository
public interface ICategoryRepository extends JpaRepository<Categories, Integer> {

}
