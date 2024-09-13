package com.ecommerce.lite.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ecommerce.lite.entities.Company;
import com.ecommerce.lite.entities.Inventory;
import com.ecommerce.lite.entities.Products;
import com.ecommerce.lite.repositories.IInventoryRepository;
import com.ecommerce.lite.service.InventoryService;

import jakarta.transaction.Transactional;

public class InventoryServiceImpl implements InventoryService {
	@Autowired
	private IInventoryRepository iInventoryRepository;

	@Override
	@Transactional
	public Inventory addProductToInventory(Company company, Products product, int quantity) {
		Inventory inventory = new Inventory();
        inventory.setCompany(company);
        inventory.setProduct(product);
        inventory.setQuantity(quantity);
        return iInventoryRepository.save(inventory);
	}

	@Override
	public List<Inventory> getProductsByCompany(Company company) {
		return iInventoryRepository.findByCompany(company);
	}
}
