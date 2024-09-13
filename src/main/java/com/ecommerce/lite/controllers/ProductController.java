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

import com.ecommerce.lite.entities.Products;
import com.ecommerce.lite.service.ProductService;

@RestController
@RequestMapping("api/business/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@PostMapping("/create")
    public ResponseEntity<Products> createProduct(@RequestBody Products product) {
        try {
            Products newProduct = productService.createProduct(product);
            
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer productId) {
        try {
            productService.deleteProduct(productId);
            
            return new ResponseEntity<>("Product deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
}
