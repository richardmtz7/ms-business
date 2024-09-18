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

import com.ecommerce.lite.entities.Products;
import com.ecommerce.lite.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/business/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@Operation(summary = "Create a product")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Created product"),
	        @ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PostMapping("/create")
    public ResponseEntity<Products> createProduct(@RequestBody Products product) {
        try {
            Products newProduct = productService.createProduct(product);
            
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@Operation(summary = "Delete a product")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Deleted product"),
	        @ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PostMapping("/delete/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer productId) {
        try {
            productService.deleteProduct(productId);
            
            return new ResponseEntity<>("Product deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@Operation(summary = "Get all product")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "List of products"),
	        @ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/getAll")
	public ResponseEntity<List<Products>> getAllProducts(){
		try {
			List<Products> products = productService.getProducts();
            
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
}
