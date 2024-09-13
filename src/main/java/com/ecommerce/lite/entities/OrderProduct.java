package com.ecommerce.lite.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "order_product")
public class OrderProduct {
	
	@Id
	private Integer orderId;
	
	@Id
	private Integer productId;
	
	@ManyToOne
	@JoinColumn(name = "orderId", insertable = false, updatable = false)
	private Orders order;
	
	@ManyToOne
	@JoinColumn(name = "productId", insertable = false, updatable = false)
	private Products product;

}
