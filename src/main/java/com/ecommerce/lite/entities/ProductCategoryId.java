package com.ecommerce.lite.entities;

import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "product_category_id")
public class ProductCategoryId {
	
	private Integer productId;
	private Integer categoryId;

}
