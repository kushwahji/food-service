/**
 * 
 */
package com.santosh.ms.food.response;

import java.math.BigDecimal;
import java.util.Optional;
import com.santosh.ms.food.client.FoodFeignClient;
import com.santosh.ms.food.client.response.FoodDto;
import com.santosh.ms.food.entity.OrderItem;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author santosh.kushwah
 *
 */
@Data
@NoArgsConstructor
public class OrderItemResponseDto {
	private BigDecimal totalPrice;
	private int quantity;
	private long productId;
	private FoodDto foods;
	
	public OrderItemResponseDto(OrderItem items,FoodFeignClient productRepository) {
		FoodDto f =productRepository.getFood(items.getProductId());
		Optional.ofNullable(f).map(p1->this.totalPrice = p1.getPrice().multiply(new BigDecimal(items.getQuantity())));
		//this.totalPrice = d.getPrice().multiply(new BigDecimal(items.getQuantity()));
		this.quantity = items.getQuantity();
		this.productId = items.getProductId();
		this.foods = new FoodDto(f);
	}
}
