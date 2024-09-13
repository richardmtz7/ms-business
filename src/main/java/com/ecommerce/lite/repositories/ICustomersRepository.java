package com.ecommerce.lite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.lite.entities.Customers;

@Repository
public interface ICustomersRepository extends JpaRepository<Customers, Integer> {

}
