package com.ecommerce.lite.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.lite.entities.Company;
import com.ecommerce.lite.service.CompanyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/api/business/company")
public class CompanyController {
	@Autowired
	private CompanyService companyService;
	
	@Operation(summary = "Create a company")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Category created"),
	        @ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PostMapping("/create")
	public ResponseEntity<Company> createCompany(@RequestBody Company company){
		try {
			Company newCompany = companyService.createCompany(company);
			
			return new ResponseEntity<>(newCompany, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "Delete a company")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Deleted company"),
	        @ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PostMapping("/delete/{nit}")
	 public ResponseEntity<String> deactivateOrActivateCompany(@PathVariable Integer nit) {
        try {
        	System.out.println(nit);
            companyService.deleteCompany(nit);
            return ResponseEntity.ok("Company delete successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating company status: " + e.getMessage());
        }
    }
	
	@Operation(summary = "Update a company")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Company updated"),
	        @ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PutMapping("/edit")
    public ResponseEntity<String> editCompany(@RequestBody Company company) {
        try {
            companyService.editCompany(company);
            return ResponseEntity.ok("Company updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating company: " + e.getMessage());
        }
    }
	
	@Operation(summary = "Get all companies")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "List of companies"),
	        @ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/getAll")
	public ResponseEntity<List<Company>> getAllCompanies(){
		try {
			List<Company> company = companyService.getAllCompanies();
            
            return new ResponseEntity<>(company, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
}	
