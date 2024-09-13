package com.ecommerce.lite.service;

import com.ecommerce.lite.entities.Categories;

public interface CategoryService {
	public Categories createCategory(Categories category) throws Exception;
	public void deleteCategoryById(Integer categoryId) throws Exception;
}
