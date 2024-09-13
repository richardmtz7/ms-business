package com.ecommerce.lite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.lite.entities.Categories;
import com.ecommerce.lite.service.CategoryService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("api/business/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/create")
	public ResponseEntity<Categories> createCategory(@RequestBody Categories category) {
		try {
			Categories createCategory = categoryService.createCategory(category);
			
			return new ResponseEntity<>(createCategory, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete/{categoryId}")
	public ResponseEntity<Void> deleteCategoryById(@PathVariable Integer categoryId){
		try {
			categoryService.deleteCategoryById(categoryId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
