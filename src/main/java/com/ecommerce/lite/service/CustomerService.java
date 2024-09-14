package com.ecommerce.lite.service;

import com.ecommerce.lite.entities.Customers;

public interface CustomerService {
    public Customers getCustomerById(Integer customerId) throws Exception;
    public void deleteCustomerById(Integer customerId) throws Exception;
}
