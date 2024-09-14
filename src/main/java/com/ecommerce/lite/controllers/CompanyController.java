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


@RestController
@RequestMapping("api/business/company")
public class CompanyController {
	@Autowired
	private CompanyService companyService;
	
	@PostMapping("/create")
	public ResponseEntity<Company> createCompany(@RequestBody Company company){
		try {
			Company newCompany = companyService.createCompany(company);
			
			return new ResponseEntity<>(newCompany, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/delete/{nit}")
	 public ResponseEntity<String> deactivateOrActivateCompany(@PathVariable Integer nit) {
        try {
            companyService.deleteCompany(nit);
            return ResponseEntity.ok("Company delete successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating company status: " + e.getMessage());
        }
    }
	
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
