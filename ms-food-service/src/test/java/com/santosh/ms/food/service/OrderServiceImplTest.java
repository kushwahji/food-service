/**
 * 
 */
package com.santosh.ms.food.service;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import com.santosh.ms.food.BaseContextLoader;
import com.santosh.ms.food.client.CartFeignClient;
import com.santosh.ms.food.client.CustomerFeignClient;
import com.santosh.ms.food.client.FoodFeignClient;
import com.santosh.ms.food.client.FundFeignClient;
import com.santosh.ms.food.client.response.FoodDto;
import com.santosh.ms.food.client.response.ItemDto;
import com.santosh.ms.food.entity.Order;
import com.santosh.ms.food.entity.OrderItem;
import com.santosh.ms.food.repository.OrderRepository;
import com.santosh.ms.food.response.AddressResponse;
import com.santosh.ms.food.response.CustomerResponse;
import com.santosh.ms.food.response.FundTransferResponse;
import com.santosh.ms.food.response.OrderResponse;

/**
 * @author santosh.kushwah
 *
 */
@SpringBootTest
public class OrderServiceImplTest  extends BaseContextLoader{

	@InjectMocks
	OrderServiceImpl service;

	@InjectMocks
	CartServiceImpl cartService;

	@Mock
	OrderRepository orderRepository;

	@Mock
	CartFeignClient cart;

	@Mock
	FoodFeignClient foodFeignClient;
	
	@Mock
	FundFeignClient fundClient;
	
	@Mock
	CustomerFeignClient cusomerClient;

	@Test
	void testPlaceOrder() {
		List<AddressResponse> adRes =  new ArrayList<>();
		AddressResponse addressResponse = new AddressResponse("datia","datia","datia","city","datia",123456,true);
		adRes.add(addressResponse);
		CustomerResponse cust = new CustomerResponse("Santosh","Kushwah",30,"santosh@gmail.com","5256723456",null);
		cust.setAddress(adRes);
		Mockito.when(cusomerClient.getCustomer(Mockito.anyString())).thenReturn(cust);
		Mockito.when(cart.getCarts(Mockito.anyString())).thenReturn(getCart());
		Mockito.when(fundClient.transfer(Mockito.any())).thenReturn(new FundTransferResponse("",""));
		Mockito.when(service.order(Mockito.anyString(), Mockito.anyLong())).thenReturn(new OrderResponse("order placed","121213232"));
		//doNothing().when(cartService).deleteCart(Mockito.anyString());
		MvcResult result = callGetEndpoint("/checkout");
		assertEquals(200, result.getResponse().getStatus());
	}

	@Test
	void testMyOrder() throws JsonMappingException, JsonProcessingException {

		List<OrderItem> itms = new ArrayList<>();
		List<Order> orders = new ArrayList<>();
		Order order = new Order();
		OrderItem itm = new OrderItem();
		itm.setId(1L);
		itm.setProductId(1L);
		itm.setQuantity(1);
		itm.setProductPrice(new BigDecimal(20));
		order.setAccountNumber(60120220152313L);
		order.setAddress("Datia");
		order.setId(1L);
		order.setLastUpdated(LocalDateTime.now());
		order.setOrderDate(LocalDateTime.now());
		order.setName("Santosh");
		order.setPayStatus("Paid");
		order.setStatus("Pending");
		order.setPhone("9752572357");
		order.setTotal(new BigDecimal(20));
		order.setTrackingNumber("123455666");
		itms.add(itm);
		order.setItems(itms);
		orders.add(order);
		FoodDto f= new FoodDto();
		f.setFid(1L);
		f.setVendorId(1L);
		f.setName("Biryani");
		f.setDescription("biryani");
		f.setPrice(new BigDecimal(200));
		Mockito.when(cart.getCarts(Mockito.anyString())).thenReturn(getCart());
		Mockito.when(cartService.getCart(Mockito.anyString())).thenReturn(getCart());
		Mockito.when(orderRepository.findByPhone(Mockito.anyString())).thenReturn(orders);
		Mockito.when(foodFeignClient.getFood(Mockito.anyLong())).thenReturn(f);
		Mockito.when(service.myOrder(Mockito.anyString())).thenReturn(null);
		MvcResult result =callGetEndpoint("/my-order");
		assertEquals(400, result.getResponse().getStatus());
	}

	public List<ItemDto> getCart() {
		List<ItemDto> items = new ArrayList<>();
		ItemDto item = new ItemDto(1, 1L, new BigDecimal(55));
		items.add(item);
		return items;
	}

}
