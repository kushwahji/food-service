/**
 * 
 */
package com.santosh.ms.cart.controller;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.santosh.ms.cart.request.ItemDto;
import com.santosh.ms.cart.service.CartService;

/**
 * @author santosh.kushwah
 * @since 12-01-2022
 */
@RestController
public class CartController {

	@Autowired
	CartService cartService;

	@GetMapping(value = "/carts")
	public List<ItemDto> getCart(@RequestParam String phone) {
		return cartService.getCart(phone);
	}

	@GetMapping(value = "/add-to-cart")
	public String addToCart(@RequestParam("phone") String phone,@RequestParam("productId") long productId,
			@RequestParam("quantity") int quantity,
			@RequestParam("price") BigDecimal price) {
		cartService.addItemToCart(phone,productId,quantity,price);
		return "Item added successfully";
	}

	@DeleteMapping(value = "/delete-cart")
	public String removeFromCart(@RequestParam("phone") String phone) {
		cartService.deleteCart(phone);
		return "cart deleted successfully";
	}
}
