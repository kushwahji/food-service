/**
 * 
 */
package com.santosh.ms.cart.service;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.santosh.ms.cart.constant.Constant;
import com.santosh.ms.cart.exception.MsApplicationException;
import com.santosh.ms.cart.helper.Helper;
import com.santosh.ms.cart.request.ItemDto;

/**
 * @author santosh.kushwah
 * @since 12-01-2022
 */
@Service
public class CartServiceImpl implements CartService {

	@Autowired
	RedisService redisService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<ItemDto> getCart(String cartId) {
		List<ItemDto> response = (List) redisService.getCart(cartId, ItemDto.class);
		if (response.isEmpty()) {
			throw new MsApplicationException(Constant.ITEM_NOT_FOUND, Constant.ITEM_NOT_FOUND_MSG);
		}
		return response;
	}

	@Override
	public void deleteCart(String cartId) {
		redisService.deleteCart(cartId);
	}
	
	@Override
	public void changeItemQuantity(String cartId, Long productId,BigDecimal price, Integer quantity) {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<ItemDto> cart = (List) redisService.getCart(cartId, ItemDto.class);
		for (ItemDto item : cart) {
			if ((item.getProductId()).equals(productId)) {
				redisService.deleteItemFromCart(cartId, item);
				item.setQuantity(quantity);
				item.setPrice(Helper.getSubTotalForItem(price, quantity));
				redisService.addItemToCart(cartId, item);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteItemFromCart(String cartId, Long productId) {
		@SuppressWarnings("rawtypes")
		List<ItemDto> cart = (List) redisService.getCart(cartId, ItemDto.class);
		for (ItemDto item : cart) {
			if ((item.getProductId()).equals(productId)) {
				redisService.deleteItemFromCart(cartId, item);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean checkIfItemIsExist(String cartId, Long productId) {
		@SuppressWarnings("unchecked")
		List<ItemDto> cart = (List) redisService.getCart(cartId, ItemDto.class);
		for (ItemDto item : cart) {
			if ((item.getProductId()).equals(productId)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void addItemToCart(String phone, long productId, int quantity, BigDecimal price) {
		redisService.addItemToCart(phone, new ItemDto(quantity,productId,price));
	}
}
