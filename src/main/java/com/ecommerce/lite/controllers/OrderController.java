package com.ecommerce.lite.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.lite.entities.Orders;
import com.ecommerce.lite.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/business/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@Operation(summary = "Create an order")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Order created"),
	        @ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody Orders order) {
        try {
            String newOrder = orderService.createOrder(order);
            
            return new ResponseEntity<>("Order ticket" + newOrder, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@Operation(summary = "Delete an order")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Deleted order"),
	        @ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PostMapping("/delete/{orderId}")
    public ResponseEntity<String> deleteOrderById(@PathVariable Integer orderId) {
        try {
            orderService.deleteOrderById(orderId);
            return new ResponseEntity<>("Order deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@Operation(summary = "Update an order")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Order updated"),
	        @ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PutMapping("/edit")
    public ResponseEntity<Orders> editOrder(@RequestBody Orders order) {
        try {
            orderService.editOrder(order);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@Operation(summary = "Get all orders")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "List of orders"),
	        @ApiResponse(responseCode = "500", description = "Internal server error")
	})
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
