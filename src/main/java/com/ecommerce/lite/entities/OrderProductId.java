package com.ecommerce.lite.entities;

import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "order_product_id")
public class OrderProductId {
	private Integer orderId;
	private Integer productId;
}
