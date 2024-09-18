package com.ecommerce.lite.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.lite.entities.Company;
import com.ecommerce.lite.repositories.ICompanyRepository;
import com.ecommerce.lite.service.CompanyService;

import jakarta.transaction.Transactional;

@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	private ICompanyRepository iCompanyRepository;
	
	@Override
	@Transactional
	public Company createCompany(Company company) throws Exception {
		Optional<Company> companyExist = iCompanyRepository.findById(company.getNit());
		try {
			if(companyExist.isEmpty()) {
				iCompanyRepository.save(company);
			} else {
				throw new Exception("Company already exist");
			}
			return company;
		} catch (Exception e) {
			throw new Exception("Error saving company" + e);
		}
	}

	@Override
	@Transactional
	public void deleteCompany(Integer companyId) throws Exception {
		try {
			Optional<Company> company = iCompanyRepository.findById(companyId);
			System.out.println(company);
			
			if(!company.isEmpty()) {
				iCompanyRepository.deleteById(companyId);
			} else {
				throw new Exception("Company not found");
			}
		} catch (Exception e) {
			throw new Exception("Error deleting company" + e);
		}
	}

	@Override
	@Transactional
	public void editCompany(Company company) throws Exception {
		iCompanyRepository.findById(company.getNit())
				.orElseThrow(() -> new NoSuchElementException("Company not found"));
		
		iCompanyRepository.save(company);
	}

	@Override
	public List<Company> getAllCompanies() {
		return iCompanyRepository.findAll();
	}

}
