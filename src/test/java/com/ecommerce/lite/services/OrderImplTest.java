package com.ecommerce.lite.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ecommerce.lite.entities.Orders;
import com.ecommerce.lite.repositories.IOrderRepository;
import com.ecommerce.lite.service.impl.OrderServiceImpl;

public class OrderImplTest {
	 	@InjectMocks
	    private OrderServiceImpl orderServiceImpl;

	    @Mock
	    private IOrderRepository iOrderRepository;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    void createOrder_success() throws Exception {
	        Orders order = new Orders();
	        String ticketOrder = UUID.randomUUID().toString();
	        doAnswer(invocation -> {
	            Orders savedOrder = invocation.getArgument(0);
	            savedOrder.setTicket(ticketOrder);
	            return savedOrder;
	        }).when(iOrderRepository).save(order);

	        String result = orderServiceImpl.createOrder(order);

	        assertNotNull(result);
	        assertEquals(ticketOrder, order.getTicket());
	        verify(iOrderRepository, times(1)).save(order);
	    }

	    @Test
	    void createOrder_exceptionWhileSaving() {
	        Orders order = new Orders();
	        when(iOrderRepository.save(order)).thenThrow(new RuntimeException("Database error"));

	        Exception exception = assertThrows(Exception.class, () -> orderServiceImpl.createOrder(order));
	        assertTrue(exception.getMessage().contains("Error creating order"));
	        verify(iOrderRepository, times(1)).save(order);
	    }

	    @Test
	    void deleteOrderById_success() throws Exception {
	        Integer orderId = 1;
	        Orders order = new Orders();
	        when(iOrderRepository.findById(orderId)).thenReturn(Optional.of(order));

	        orderServiceImpl.deleteOrderById(orderId);

	        verify(iOrderRepository, times(1)).findById(orderId);
	        verify(iOrderRepository, times(1)).deleteById(orderId);
	    }

	    @Test
	    void deleteOrderById_orderNotFound() {
	        Integer orderId = 1;
	        when(iOrderRepository.findById(orderId)).thenReturn(Optional.empty());

	        Exception exception = assertThrows(Exception.class, () -> orderServiceImpl.deleteOrderById(orderId));
	        assertTrue(exception.getMessage().contains("Order not found"));
	        verify(iOrderRepository, times(1)).findById(orderId);
	        verify(iOrderRepository, never()).deleteById(orderId);
	    }

	    @Test
	    void editOrder_success() throws Exception {
	        Orders order = new Orders();
	        order.setOrderId(1);
	        order.setProductId(2);

	        Orders existingOrder = new Orders();
	        existingOrder.setOrderId(1);

	        when(iOrderRepository.findById(order.getOrderId())).thenReturn(Optional.of(existingOrder));

	        orderServiceImpl.editOrder(order);

	        verify(iOrderRepository, times(1)).findById(order.getOrderId());
	        assertEquals(order.getProductId(), existingOrder.getProductId());
	        verify(iOrderRepository, times(1)).save(existingOrder);
	    }

	    @Test
	    void editOrder_orderNotFound() {
	        Orders order = new Orders();
	        order.setOrderId(1);
	        when(iOrderRepository.findById(order.getOrderId())).thenReturn(Optional.empty());

	        Exception exception = assertThrows(Exception.class, () -> orderServiceImpl.editOrder(order));
	        assertTrue(exception.getMessage().contains("Order not found"));
	        verify(iOrderRepository, times(1)).findById(order.getOrderId());
	        verify(iOrderRepository, never()).save(order);
	    }

	    @Test
	    void editOrder_orderOrOrderIdIsNull() {
	        Exception exception1 = assertThrows(Exception.class, () -> orderServiceImpl.editOrder(null));
	        assertTrue(exception1.getMessage().contains("Order or order ID cannot be null"));

	        Orders orderWithNullId = new Orders();
	        Exception exception2 = assertThrows(Exception.class, () -> orderServiceImpl.editOrder(orderWithNullId));
	        assertTrue(exception2.getMessage().contains("Order or order ID cannot be null"));
	    }

	    @Test
	    void getAllOrders_success() {
	        List<Orders> ordersList = new ArrayList<>();
	        ordersList.add(new Orders());
	        when(iOrderRepository.findAll()).thenReturn(ordersList);

	        List<Orders> result = orderServiceImpl.getAllOrders();

	        assertNotNull(result);
	        assertEquals(1, result.size());
	        verify(iOrderRepository, times(1)).findAll();
	    }
}
