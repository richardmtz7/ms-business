package com.ecommerce.lite.service;

import com.ecommerce.lite.entities.Orders;

public interface OrderService {
	public Orders createOrder(Orders order) throws Exception;
	public void deleteOrderById(Integer orderId) throws Exception;
	public void editOrder(Orders order) throws Exception;
}
