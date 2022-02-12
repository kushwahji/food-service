/**
 * 
 */
package com.santosh.ms.food.client.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author santosh.kushwah
 * @since 15-01-2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
	private int quantity;
	private Long productId;
	private BigDecimal price;
}
