package com.ecommerce.lite.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "products")
public class Products {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productId;
	
	@Column(name = "productName")
	private String productName;
	
	@Column(name = "characteristics")
	private String characteristics;
	
	@Column(name = "price")
	private Double price;
	
	@Column(name = "companyNit")
	private Integer companyNit;
	
	@Column(name = "categoryId")
	private Integer categoryId;
}
