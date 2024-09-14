package com.ecommerce.lite.service;

import java.util.List;

import com.ecommerce.lite.entities.Orders;

public interface OrderService {
	public String createOrder(Orders order) throws Exception;
	public void deleteOrderById(Integer orderId) throws Exception;
	public void editOrder(Orders order) throws Exception;
	public List<Orders> getAllOrders();
}
