package com.ecommerce.lite.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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
@IdClass(ProductCategoryId.class)
@Table(name = "product_category")
public class ProductCategory {
	
	@Id
    private Integer productId;

    @Id
    private Integer categoryId;

    @ManyToOne
    @JoinColumn(name = "productId", insertable = false, updatable = false)
    private Products product;

    @ManyToOne
    @JoinColumn(name = "categoryId", insertable = false, updatable = false)
    private Categories category;
    
}
