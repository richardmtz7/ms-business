package com.ecommerce.lite.service;

import java.util.List;

import com.ecommerce.lite.entities.Products;

public interface ProductService {
	public Products createProduct(Products product) throws Exception;
	public void deleteProduct(Integer productId) throws Exception;
	public List<Products> getProducts();
}
