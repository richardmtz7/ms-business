package com.ecommerce.lite.entities;

import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "customer_order_id")
public class CustomerOrderId {
	private Integer customerId;
	private Integer orderId;
}
