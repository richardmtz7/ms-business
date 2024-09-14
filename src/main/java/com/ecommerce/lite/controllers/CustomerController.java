package com.ecommerce.lite.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.lite.entities.Customers;
import com.ecommerce.lite.service.CustomerService;

@RestController
@RequestMapping("/api/business/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/{customerId}")
    public ResponseEntity<Customers> getCustomerById(@PathVariable Integer customerId) {
        try {
            Customers customer = customerService.getCustomerById(customerId);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	@DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable Integer customerId) {
        try {
            customerService.deleteCustomerById(customerId);
            return new ResponseEntity<>("Customer deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
