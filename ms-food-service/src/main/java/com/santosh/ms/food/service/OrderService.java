/**
 * 
 */
package com.santosh.ms.food.service;


import com.santosh.ms.food.response.OrderHistoryResponse;
import com.santosh.ms.food.response.OrderResponse;

/**
 * @author santosh.kushwah
 * @since 12-01-2022
 */
public interface OrderService {
	
	OrderHistoryResponse myOrder(String phone);

	OrderResponse order(String phone, long accountNumber);
}
