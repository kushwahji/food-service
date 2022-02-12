/**
 * 
 */
package com.santosh.ms.food.controller;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.santosh.ms.food.response.OrderHistoryResponse;
import com.santosh.ms.food.response.OrderResponse;
import com.santosh.ms.food.service.OrderService;

/**
 * @author santosh.kushwah
 * @since 12-01-2022
 */
@RestController
public class OrderController {
	
	@Autowired
	OrderService orderService;
		
	@GetMapping("/checkout")
	public OrderResponse order(@Valid @RequestParam("phone") String phone, @RequestParam("accountNumber") long accountNumber) {
		return orderService.order(phone,accountNumber);
	}

	@GetMapping("/my-order")
	public OrderHistoryResponse myOrder(@RequestParam String phone){
		return orderService.myOrder(phone);
	}
}
