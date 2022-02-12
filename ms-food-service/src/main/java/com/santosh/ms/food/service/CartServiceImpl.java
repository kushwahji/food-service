/**
 *
 */
package com.santosh.ms.food.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.santosh.ms.food.client.CartFeignClient;
import com.santosh.ms.food.client.FoodFeignClient;
import com.santosh.ms.food.client.response.FoodDto;
import com.santosh.ms.food.client.response.ItemDto;
import com.santosh.ms.food.constant.Constant;
import com.santosh.ms.food.exception.MsApplicationException;
import com.santosh.ms.food.helper.Helper;


/**
 * @author santosh.kushwah
 * @since 12-01-2022
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartFeignClient cartService;

    @Autowired
    FoodFeignClient foodFeignClient;

    @Autowired
    Helper helper;

    @Override
    public List<ItemDto> getCart(String phone) {
        return Optional.ofNullable(cartService.getCarts(phone)).orElseThrow(() -> new MsApplicationException(Constant.ITEM_NOT_FOUND,
                Constant.ITEM_NOT_FOUND_MSG));
    }

    @Override
    public void deleteCart(String phone) {
        cartService.deleteCart(phone);
    }

    public FoodDto getFood(long productId) {
        return foodFeignClient.getFood(productId);

    }


    @Override
    public void addItemToCart(String phone, Long productId, Integer quantity) {
        FoodDto food = Optional.ofNullable(getFood(productId)).filter(f -> f.getFid().equals(productId)).orElseThrow(() -> new MsApplicationException(Constant.PRODUCT_NOT_FOUND, Constant.PRODUCT_NOT_FOUND_MSG.replace("PID",""+productId)));
        ItemDto item = new ItemDto(quantity, food.getFid(),
                Helper.getSubTotalForItem(food.getPrice(), quantity));
        cartService.addToCart(phone, productId, item.getQuantity(), item.getPrice());
    }

}
