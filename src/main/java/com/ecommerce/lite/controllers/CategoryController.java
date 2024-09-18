package com.ecommerce.lite.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.lite.entities.Categories;
import com.ecommerce.lite.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/business/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@Operation(summary = "Create a category")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Category create"),
	        @ApiResponse(responseCode = "500", description = "Internal server error")
	})
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
	
	@Operation(summary = "Delete a category")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Deleted category"),
	        @ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PostMapping("/delete/{categoryId}")
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
	
	@Operation(summary = "Get all categories")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "List of categories"),
	        @ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/getAll")
	public ResponseEntity<List<Categories>> getAllProducts(){
		try {
			List<Categories> categories = categoryService.getAllCategories();
            
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
}
