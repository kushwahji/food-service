/**
 * 
 */
package com.santosh.ms.food.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import com.santosh.ms.food.client.FoodFeignClient;
import com.santosh.ms.food.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author santosh.kushwah
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderHistoryResponse {
	private List<OrderDetails> orderDetails;
	public OrderHistoryResponse(Order order) {
		
	}
	
    public OrderHistoryResponse(String phone, List<OrderDetails> odDetails) {
		this.orderDetails = odDetails;	
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class OrderDetails{
		private String phone;
		private String customer;
		private String status;
		private BigDecimal totalAmount;
		private String payStatus;
		private String orderTrackingId;
		private String shippingAddress;
		List<OrderItemResponseDto> items;
		
		public OrderDetails(Order order,FoodFeignClient productRepository) {
			this.phone =order.getPhone();
			this.totalAmount = order.getTotal();
			this.customer = order.getName();
			this.status = order.getStatus();
			this.payStatus = order.getPayStatus();
			this.orderTrackingId = order.getTrackingNumber();
			this.shippingAddress = order.getAddress();
			this.items = order.getItems().stream().map(it->new OrderItemResponseDto(it,productRepository)).collect(Collectors.toList());
		}
	}
}
