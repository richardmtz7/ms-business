package com.ecommerce.lite.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.lite.entities.Orders;
import com.ecommerce.lite.service.OrderService;

@RestController
@RequestMapping("/api/business/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody Orders order) {
        try {
            String newOrder = orderService.createOrder(order);
            
            return new ResponseEntity<>("Order ticket" + newOrder, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@DeleteMapping("/delete/{orderId}")
    public ResponseEntity<String> deleteOrderById(@PathVariable Integer orderId) {
        try {
            orderService.deleteOrderById(orderId);
            return new ResponseEntity<>("Order deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@PutMapping("/edit")
    public ResponseEntity<Orders> editOrder(@RequestBody Orders order) {
        try {
            orderService.editOrder(order);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Orders>> getAllOrders(){
		try {
			List<Orders> orders = orderService.getAllOrders();
            
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
}
