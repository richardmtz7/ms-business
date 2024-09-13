package com.ecommerce.lite.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.lite.entities.Categories;
import com.ecommerce.lite.repositories.ICategoryRepository;
import com.ecommerce.lite.service.CategoryService;

import jakarta.transaction.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private ICategoryRepository iCategoryRepository;
	
	@Override
	@Transactional
	public Categories createCategory(Categories category) throws Exception {
		try {
			iCategoryRepository.save(category);
			return category;
		} catch (Exception e) {
			throw new Exception("Error creating category" + e);
		}
	}

	@Override
	@Transactional
	public void deleteCategoryById(Integer categoryId) throws Exception {
		Optional<Categories> categoryExist = iCategoryRepository.findById(categoryId);
		
		try {
			if(!categoryExist.isEmpty()) {
				iCategoryRepository.deleteById(categoryId);
			}
		} catch (Exception e) {
			throw new Exception("Error deleting category" + e);
		}
	}

}
