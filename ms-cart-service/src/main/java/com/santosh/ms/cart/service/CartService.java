package com.santosh.ms.cart.service;
/**
 * 
 */

import java.math.BigDecimal;
import java.util.List;

import com.santosh.ms.cart.request.ItemDto;

/**
 * @author santosh.kushwah
 * @since 12-01-2022
 */
public interface CartService {

	public List<ItemDto> getCart(String phone);

	public void changeItemQuantity(String phone, Long productId, BigDecimal price, Integer quantity);

	public void deleteItemFromCart(String phone, Long productId);

	public boolean checkIfItemIsExist(String phone, Long productId);

	public void deleteCart(String phone);

	public void addItemToCart(String phone, long productId, int quantity, BigDecimal price);

}
