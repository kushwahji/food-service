/**
 * 
 */
package com.santosh.ms.food.service;

import java.util.List;

import com.santosh.ms.food.client.response.ItemDto;


/**
 * @author santosh.kushwah
 * @since 12-01-2022
 */
public interface CartService {

	public void addItemToCart(String phone, Long productId, Integer quantity);

	public List<ItemDto> getCart(String phone);

	public void deleteCart(String phone);

}
