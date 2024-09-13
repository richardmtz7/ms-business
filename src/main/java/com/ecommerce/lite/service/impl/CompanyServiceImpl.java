package com.ecommerce.lite.service.impl;

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
	public void deactivateOrActivateCompany(Integer companyId) throws Exception {
		try {
			Company company = iCompanyRepository.findById(companyId)
					.orElseThrow(() -> new NoSuchElementException("Company not found"));
			if(company.getStatus() == 1) {
				company.setStatus(0);
			} else if(company.getStatus() == 0) {
				company.setStatus(1);
			}
			iCompanyRepository.save(company);
		} catch (Exception e) {
			throw new Exception("Error deactivating company" + e);
		}
	}

	@Override
	@Transactional
	public void editCompany(Company company) throws Exception {
		Company companyExist = iCompanyRepository.findById(company.getNit())
				.orElseThrow(() -> new NoSuchElementException("Company not found"));
		
		if(company.getCompanyName() != companyExist.getCompanyName() ) {
			companyExist.setCompanyName(company.getCompanyName());
		}
		
		if(company.getPhone() != companyExist.getPhone()) {
			companyExist.setPhone(company.getPhone());
		}
		
		// If the nit is different or not, it is still sent to the company found 
		companyExist.setNit(company.getNit());
		
		iCompanyRepository.save(companyExist);
	}

}
