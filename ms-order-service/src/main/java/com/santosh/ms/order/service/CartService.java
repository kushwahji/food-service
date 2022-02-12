/**
 * 
 */
package com.santosh.ms.order.service;

import com.santosh.ms.order.client.response.ItemDto;

import java.util.List;


/**
 * @author santosh.kushwah
 * @since 12-01-2022
 */
public interface CartService {

	public void addItemToCart(String phone, Long productId, Integer quantity);

	public List<ItemDto> getCart(String phone);

	public void deleteCart(String phone);

}
