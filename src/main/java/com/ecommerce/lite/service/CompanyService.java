package com.ecommerce.lite.service;

import com.ecommerce.lite.entities.Company;

public interface CompanyService {
	public Company createCompany(Company company) throws Exception;
	public void deactivateOrActivateCompany(Integer companyId) throws Exception;
	public void editCompany(Company company) throws Exception;
}
