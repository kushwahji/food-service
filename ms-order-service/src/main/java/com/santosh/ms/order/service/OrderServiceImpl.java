package com.santosh.ms.order.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import com.santosh.ms.order.response.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.santosh.ms.order.client.CustomerFeignClient;
import com.santosh.ms.order.client.FundFeignClient;
import com.santosh.ms.order.client.ProductFeignClient;
import com.santosh.ms.order.constant.Constant;
import com.santosh.ms.order.entity.Order;
import com.santosh.ms.order.exception.MsApplicationException;
import com.santosh.ms.order.repository.OrderRepository;
import com.santosh.ms.order.request.FundTransferDto;
import com.santosh.ms.order.client.response.ItemDto;
import com.santosh.ms.order.response.OrderHistoryResponse;
import com.santosh.ms.order.response.OrderHistoryResponse.OrderDetails;
import com.santosh.ms.order.response.OrderResponse;

/**
 * @author santosh.kushwah
 * @since 15-01-2022
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Value("${enterprise_account_number}")
	private long enterpriseAccount;

	@Autowired
	CustomerFeignClient customerFeignClient;

	@Autowired
	FundFeignClient fundFeignClient;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	CartService cartService;
	
	@Autowired
	ProductFeignClient productFeignClient;

	@Override
	public OrderResponse order(String phone, long accountNumber) {
		getCustomer(phone);
		List<ItemDto> carts = cartService.getCart(phone);
		if (!carts.isEmpty()) {
			return new OrderResponse(Constant.ORDER_PLACED,placeOrder(phone, accountNumber, carts));
		} else {
			throw new MsApplicationException(Constant.ITEM_NOT_FOUND, Constant.ITEM_NOT_FOUND_MSG);
		}

	}

	@Override
	public OrderHistoryResponse myOrder(String phone) {
		List<Order> orders = orderRepository.findByPhone(phone);
		if(orders.isEmpty()) {
			throw new MsApplicationException(Constant.ORDER_NOT_FOUND,Constant.ORDER_NOT_FOUND_MSG.replace("PHONE",phone));
		}
		return new OrderHistoryResponse(phone, orders.stream().map(o->new OrderDetails(o,productFeignClient)).collect(Collectors.toList()));
	}

	private String placeOrder(String phone, long accountNumber, List<ItemDto> carts) {
		Order order = new Order(getCustomer(phone), accountNumber, carts);
		payment(accountNumber, order.getTotal(), "Place Order");
		orderRepository.save(order);
		cartService.deleteCart(phone);
		return order.getTrackingNumber();

	}

	public void payment(long accountNumber, BigDecimal amount, String remarks) {
		if (amount.doubleValue() > 50) {
			fundFeignClient
					.transfer(new FundTransferDto(accountNumber, enterpriseAccount, amount.doubleValue(), remarks));
		} else {
			throw new MsApplicationException(Constant.MINIMUM_AMONT,
					Constant.MINIMUM_AMONT_MSG);

		}
	}
	public CustomerResponse getCustomer(String phone) {
		CustomerResponse customer = null;
		try {
			customer = customerFeignClient.getCustomer(phone);
			if (customer == null) {
				throw new MsApplicationException(Constant.CUSTOMER_NOT_FOUND, Constant.CUSTOMER_NOT_FOUND_MSG);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
	}

}
