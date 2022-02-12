/**
 * 
 */
package com.santosh.ms.cart.service;

import java.util.List;

/**
 * @author santosh.kushwah
 * @since 14-01-2022
 */
public interface RedisService {
	public void addItemToCart(String key, Object item);

	public List<Object> getCart(String key, @SuppressWarnings("rawtypes") Class type);

	public void deleteItemFromCart(String key, Object item);

	public void deleteCart(String key);
}
