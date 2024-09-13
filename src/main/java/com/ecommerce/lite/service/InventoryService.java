package com.ecommerce.lite.service;

import java.util.List;

import com.ecommerce.lite.entities.Company;
import com.ecommerce.lite.entities.Inventory;
import com.ecommerce.lite.entities.Products;

public interface InventoryService {
	public Inventory addProductToInventory(Company company, Products product, int quantity);
	public List<Inventory> getProductsByCompany(Company company);
}
