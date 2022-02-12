/**
 * 
 */
package com.santosh.ms.food.client.response;

import java.math.BigDecimal;
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
	
	public FoodDto(FoodDto f) {
		this.fid = f.getFid();
		this.vendorId = f.getVendorId();
		this.name = f.getName();
		this.description = f.getDescription();
		this.price = f.getPrice()	;

	}
}
