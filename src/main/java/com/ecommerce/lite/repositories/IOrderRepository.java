package com.ecommerce.lite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.lite.entities.Orders;

@Repository
public interface IOrderRepository extends JpaRepository<Orders, Integer>{

}
