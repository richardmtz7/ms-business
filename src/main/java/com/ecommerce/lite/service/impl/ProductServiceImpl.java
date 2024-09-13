package com.ecommerce.lite.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.lite.entities.Products;
import com.ecommerce.lite.repositories.IProductRepository;
import com.ecommerce.lite.service.ProductService;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private IProductRepository iProductRepository;
	
	@Override
	@Transactional
	public Products createProduct(Products product) throws Exception {
		if(product.getCharacteristics() == null || product.getCompany() == null 
				|| product.getProductCategories() == null || product.getProductName() == null) {
			throw new Exception("Some especifications cannot be null or empty");
		}

		try {
			iProductRepository.save(product);
			
			return product;
		} catch (Exception e) {
			throw new Exception("Error creating product" + e);
		}
		
	}

	@Override
	@Transactional
	public void deleteProduct(Integer productId) throws Exception {
		Optional<Products> productExist = iProductRepository.findById(productId);
		try {
			if(!productExist.isEmpty()) {
				iProductRepository.deleteById(productId);
			}
		} catch (Exception e) {
			throw new Exception("Error deleting product" + e);
		}
	}

}
