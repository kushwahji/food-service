package com.santosh.ms.food.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.santosh.ms.food.client.response.FoodDto;


@FeignClient(name = "http://MS-FOOD-PRODUCT/food/product")
public interface FoodFeignClient {

	@GetMapping("/")
	public FoodDto getFood(@RequestParam("id") long id);
}
