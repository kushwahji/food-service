/**
 * 
 */
package com.santosh.ms.food.product.dto;

import java.math.BigDecimal;
import com.santosh.ms.food.product.entity.Food;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author santosh.kushwah
 * @since 27-01-2022
 */
@Data
@NoArgsConstructor
public class FoodDto {
	private Long fid;
	private Long vendorId;
	private String name;
	private String description;
	private BigDecimal price;
	
	public FoodDto(Food food) {
		this.fid = food.getFid();
		this.vendorId = food.getVendorId();
		this.name = food.getName();
		this.description = food.getDescription();
		this.price = food.getPrice();
	}
}
