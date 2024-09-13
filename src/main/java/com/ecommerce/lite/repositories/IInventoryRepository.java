package com.ecommerce.lite.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.lite.entities.Company;
import com.ecommerce.lite.entities.Inventory;

public interface IInventoryRepository extends JpaRepository<Inventory, Integer>{
	List<Inventory> findByCompany(Company company);
}
