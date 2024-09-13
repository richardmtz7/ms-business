package com.ecommerce.lite.service;

import com.ecommerce.lite.entities.Products;

public interface ProductService {
	public Products createProduct(Products product) throws Exception;
	public void deleteProduct(Integer productId) throws Exception;
}
