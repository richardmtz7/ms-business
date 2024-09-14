package com.ecommerce.lite.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.lite.entities.Orders;
import com.ecommerce.lite.repositories.IOrderRepository;
import com.ecommerce.lite.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private IOrderRepository iOrderRepository;
	
	@Override
	@Transactional
	public String createOrder(Orders order) throws Exception {
		if (order.getProductId() == null) {
            throw new Exception("Order or order products cannot be null or empty");
        }
		
		String ticketOrder = UUID.randomUUID().toString();
		order.setTicket(ticketOrder);
		
		try {
			iOrderRepository.save(order);
			return ticketOrder;
		} catch (Exception e) {
			throw new Exception("Error creating order" + e);
		}
	}

	@Override
	@Transactional
	public void deleteOrderById(Integer orderId) throws Exception {
		Optional<Orders> order = iOrderRepository.findById(orderId);
        if (order.isPresent()) {
        	iOrderRepository.deleteById(orderId);
        } else {
            throw new Exception("Order not found");
        }
	}

	@Override
	@Transactional
	public void editOrder(Orders order) throws Exception {
		if (order == null || order.getOrderId() == null) {
            throw new Exception("Order or order ID cannot be null");
        }
		
		Optional<Orders> existingOrder = iOrderRepository.findById(order.getOrderId());
		try {
			if (existingOrder.isPresent()) {
	            Orders updatedOrder = existingOrder.get();
	            updatedOrder.setProductId(order.getProductId());
	            
	            iOrderRepository.save(updatedOrder);
	        } else {
	        	throw new Exception("Order not found");
	        }
		} catch (Exception e) {
			throw new Exception("Error updating order" + e);
		}
        
	}

	@Override
	public List<Orders> getAllOrders() {
		return iOrderRepository.findAll();
	}
	
	
}
