package com.santosh.ms.food.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.santosh.ms.food.BaseContextLoader;
import com.santosh.ms.food.client.CartFeignClient;
import com.santosh.ms.food.client.response.ItemDto;

class CartServiceImplTest extends BaseContextLoader {

	@Mock
	CartServiceImpl cartServiceImpl;

	@Mock
	CartFeignClient cartService;

	@Test
	public void getCart() throws JsonMappingException, JsonProcessingException {
		List<ItemDto> itm = new ArrayList<>();
		itm.add(new ItemDto(1, 1L, new BigDecimal(20)));
		Mockito.when(cartServiceImpl.getCart("7987610989")).thenReturn(itm);
		MvcResult result = callGetEndpoint("/cart/carts");
		assertEquals(400, result.getResponse().getStatus());
	}

	@Test
	void test_add_to_cart() {
		cartService.addToCart(Mockito.anyString(), Mockito.anyLong(), Mockito.anyInt(),
				new BigDecimal(Mockito.anyDouble()));
		doNothing().when(cartServiceImpl).addItemToCart(Mockito.anyString(), Mockito.anyLong(), Mockito.anyInt());
		MvcResult result = callGetEndpoint("/cart/add-to-cart?phone=7987610989&productId=2&quantity=2");
		assertEquals(201, result.getResponse().getStatus());
	}
	
	@Test
	void testDeleteCart() {
		cartService.deleteCart(Mockito.anyString());
		doNothing().when(cartServiceImpl).deleteCart(Mockito.anyString());
		MvcResult result = callGetEndpoint("/cart/delete-cart?phone=9752572357");
		assertEquals(405, result.getResponse().getStatus());
	}
}
