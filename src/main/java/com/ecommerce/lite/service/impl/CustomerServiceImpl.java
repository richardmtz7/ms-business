package com.ecommerce.lite.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.lite.entities.Customers;
import com.ecommerce.lite.repositories.ICustomersRepository;
import com.ecommerce.lite.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private ICustomersRepository iCustomersRepository;

	@Override
	public Customers getCustomerById(Integer customerId) throws Exception {
		Customers customer =iCustomersRepository.findById(customerId).orElseThrow(null); 
		return customer;
	}

	@Override
	public void deleteCustomerById(Integer customerId) throws Exception {
		try {
			Optional<Customers> customerExist = iCustomersRepository.findById(customerId);
			
			if(!customerExist.isEmpty()) {
				iCustomersRepository.deleteById(customerId);
			}
		} catch (Exception e) {
			throw new Exception("Error deleting customer" + e);
		}
	}
}
