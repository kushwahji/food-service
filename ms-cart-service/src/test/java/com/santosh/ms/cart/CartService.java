/**
 * 
 */
package com.santosh.ms.cart;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.test.web.servlet.MvcResult;

import com.santosh.ms.cart.request.ItemDto;
import com.santosh.ms.cart.service.CartServiceImpl;
import com.santosh.ms.cart.service.RedisServiceImpl;
import com.santosh.ms.cart.utils.JsonUtil;

/**
 * @author santosh.kushwah
 * @since 30-01-2022
 */
public class CartService extends BaseContextLoader {

	@MockBean
	CartServiceImpl cartServiceImpl;
	
	@MockBean
	RedisServiceImpl redisServiceImpl;
	
	@Test
	public void test() {
		List<ItemDto> itm = new ArrayList<>();
		List<Object> obj = new ArrayList<>();
		itm.add(new ItemDto(1, 1L, new BigDecimal(20)));
		//Mockito.when(redisServiceImpl.getCart(Mockito.anyString(), Mockito.any())).thenReturn(null);
		//doThrow(new RuntimeException()).when(cartServiceImpl.getCart(Mockito.anyString()));
		Mockito.when(cartServiceImpl.getCart("7987610989")).thenReturn(itm);
		MvcResult result = callGetEndpoint("/carts");
		assertEquals(200, result.getResponse().getStatus());
	}
}
