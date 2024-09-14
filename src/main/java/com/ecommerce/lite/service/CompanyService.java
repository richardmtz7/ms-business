package com.ecommerce.lite.service;

import java.util.List;

import com.ecommerce.lite.entities.Company;

public interface CompanyService {
	public Company createCompany(Company company) throws Exception;
	public void deleteCompany(Integer companyId) throws Exception;
	public void editCompany(Company company) throws Exception;
	public List<Company> getAllCompanies();
}
