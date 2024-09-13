package com.ecommerce.lite.service;

import java.util.List;

import com.ecommerce.lite.entities.Customers;
import com.ecommerce.lite.entities.Orders;

public interface CustomerService {
	public Customers createCustomer(Customers customer) throws Exception;
    public Customers getCustomerById(Integer customerId) throws Exception;
    public void deleteCustomerById(Integer customerId) throws Exception;
    public List<Orders> getOrdersByCustomerId(Integer customerId) throws Exception;
}
