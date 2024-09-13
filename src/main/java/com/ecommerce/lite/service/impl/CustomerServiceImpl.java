package com.ecommerce.lite.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.lite.entities.Customers;
import com.ecommerce.lite.entities.Orders;
import com.ecommerce.lite.repositories.ICustomersRepository;
import com.ecommerce.lite.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private ICustomersRepository iCustomersRepository;

	@Override
	@Transactional
	public Customers createCustomer(Customers customer) throws Exception {
		if (customer == null || customer.getEmail() == null) {
            throw new Exception("Customer or email cannot be null");
        }
        iCustomersRepository.save(customer);
        return customer;
	}

	@Override
	public Customers getCustomerById(Integer customerId) throws Exception {
		Optional<Customers> customer = iCustomersRepository.findById(customerId);
		if(customer.isPresent()) {
			return customer.get();
		} else {
			throw new Exception("Customer not found");
		}
	}

	@Override
	@Transactional
	public void deleteCustomerById(Integer customerId) throws Exception {
		Optional<Customers> customer = iCustomersRepository.findById(customerId);
		if (customer.isPresent()) {
            iCustomersRepository.deleteById(customerId);
        } else {
            throw new Exception("Customer not found");
        }
	}

	@Override
	public List<Orders> getOrdersByCustomerId(Integer customerId) throws Exception {
		Optional<Customers> customer = iCustomersRepository.findById(customerId);
        if (customer.isPresent()) {
            return customer.get().getOrders();
        } else {
            throw new Exception("Customer not found");
        }
	}
}
