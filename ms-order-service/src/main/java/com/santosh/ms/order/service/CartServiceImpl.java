/**
 * 
 */
package com.santosh.ms.order.service;

import com.santosh.ms.order.client.CartFeignClient;
import com.santosh.ms.order.client.ProductFeignClient;
import com.santosh.ms.order.client.response.ItemDto;
import com.santosh.ms.order.client.response.ProductDto;
import com.santosh.ms.order.constant.Constant;
import com.santosh.ms.order.entity.Product;
import com.santosh.ms.order.exception.MsApplicationException;
import com.santosh.ms.order.helper.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author santosh.kushwah
 * @since 12-01-2022
 */
@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartFeignClient cartService;

	@Autowired
	ProductFeignClient productFeignClient;
	
	@Autowired
	Helper helper;

	@Override
	public List<ItemDto> getCart(String phone) {
		List<ItemDto> response =  cartService.getCarts(phone);
		if (response.isEmpty()) {
			throw new MsApplicationException(Constant.ITEM_NOT_FOUND,
					Constant.ITEM_NOT_FOUND_MSG);
		}
		return response;
	}

	@Override
	public void deleteCart(String phone) {
		cartService.deleteCart(phone);
	}

	public ProductDto getProduct(long productId) {
		return productFeignClient.getProducts(productId);
	}
	

	@Override
	public void addItemToCart(String phone, Long productId, Integer quantity) {
		ProductDto f = getProduct(productId);
		if(f.getProductId()!=(productId)) {
			throw new MsApplicationException("No Product", "No Product for this productId "+productId);
		}
		ItemDto item = new ItemDto(quantity, f.getProductId(),
				Helper.getSubTotalForItem(f.getPrice(), quantity));
		cartService.addToCart(phone, productId,item.getQuantity(),item.getPrice());
	}

}
