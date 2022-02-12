package com.santosh.ms.food.client;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.santosh.ms.food.client.response.ItemDto;

@FeignClient(name = "http://MS-CART-SERVICE/cart/")
public interface CartFeignClient {

	@GetMapping("carts/")
	List<ItemDto> getCarts(@RequestParam String phone);

	@GetMapping(value = "add-to-cart")
	public String addToCart(@RequestParam("phone") String phone,@RequestParam("productId") long productId,
			@RequestParam("quantity") int quantity,
			@RequestParam("price") BigDecimal price);

	@DeleteMapping(value = "delete-cart")
	public String deleteCart(@RequestParam("phone") String phone);

}
