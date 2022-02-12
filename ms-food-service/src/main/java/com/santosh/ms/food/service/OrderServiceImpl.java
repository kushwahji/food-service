/**
 *
 */
package com.santosh.ms.food.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.santosh.ms.food.client.CartFeignClient;
import com.santosh.ms.food.client.CustomerFeignClient;
import com.santosh.ms.food.client.FoodFeignClient;
import com.santosh.ms.food.client.FundFeignClient;
import com.santosh.ms.food.client.response.FoodDto;
import com.santosh.ms.food.client.response.ItemDto;
import com.santosh.ms.food.constant.Constant;
import com.santosh.ms.food.entity.Order;
import com.santosh.ms.food.exception.MsApplicationException;
import com.santosh.ms.food.helper.Helper;
import com.santosh.ms.food.repository.OrderRepository;
import com.santosh.ms.food.request.FundTransferDto;
import com.santosh.ms.food.response.CustomerResponse;
import com.santosh.ms.food.response.OrderHistoryResponse;
import com.santosh.ms.food.response.OrderResponse;
import com.santosh.ms.food.response.OrderHistoryResponse.OrderDetails;

/**
 * @author santosh.kushwah
 * @since 15-01-2022
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Value("${enterprise_account_number}")
    private long toAccountNumber;
    @Autowired
    CustomerFeignClient customerFeignClient;
    @Autowired
    FundFeignClient fundFeignClient;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CartFeignClient cartService;
    @Autowired
    FoodFeignClient foodFeignClient;
    @Autowired
    Helper helper;

    @Transactional
    @Override
    public OrderResponse order(String phone, long accountNumber) {
        getCustomer(phone);
        return new OrderResponse(Constant.ORDER_PLACED, placeOrder(phone, accountNumber, (Optional.ofNullable(cartService.getCarts(phone)).orElseThrow(() -> new MsApplicationException(Constant.ITEM_NOT_FOUND, Constant.ITEM_NOT_FOUND_MSG)))));
    }

    @Override
    public OrderHistoryResponse myOrder(String phone) {
        return new OrderHistoryResponse(phone, Optional.ofNullable(orderRepository.findByPhone(phone)).filter(f -> f.size() > 0).orElseThrow(() -> new MsApplicationException(Constant.ORDER_NOT_FOUND,
                Constant.ORDER_NOT_FOUND_MSG.replace("PHONE", phone))).stream().map(o -> new OrderDetails(o, foodFeignClient)).collect(Collectors.toList()));
    }

    private String placeOrder(String phone, long accountNumber, List<ItemDto> carts) {
        Order order = new Order(getCustomer(phone), accountNumber, carts);
        payment(accountNumber, order.getTotal(), "Place Order");
        orderRepository.save(order);
        cartService.deleteCart(phone);
        return order.getTrackingNumber();

    }

    public void payment(long accountNumber, BigDecimal amount, String remarks) {
        Optional.ofNullable(amount).filter(f -> f.doubleValue() > 50).orElseThrow(() -> new MsApplicationException(Constant.MINIMUM_AMONT, Constant.MINIMUM_AMONT_MSG));
        fundFeignClient
                .transfer(new FundTransferDto(accountNumber, toAccountNumber, amount.doubleValue(), remarks));
    }

    @Cacheable(value = "productResponseDtoCache", key = "#productId")
    public FoodDto getProducts(long productId) {
        return foodFeignClient.getFood(productId);
    }

    public CustomerResponse getCustomer(String phone) {
        return Optional.ofNullable(customerFeignClient.getCustomer(phone)).orElseThrow(() -> new MsApplicationException(Constant.CUSTOMER_NOT_FOUND, Constant.CUSTOMER_NOT_FOUND_MSG));
    }
}
