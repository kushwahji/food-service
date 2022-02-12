/**
 * 
 */
package com.santosh.ms.food.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.santosh.ms.food.product.dto.FoodDto;
import com.santosh.ms.food.product.service.FoodService;

/**
 * @author santosh.kushwah
 * @since 28-01-2022
 */
@RestController
public class FoodController {

	@Autowired
	FoodService foodService;

	@GetMapping("/search")
	public List<FoodDto> foods(@RequestParam("name") String name) {
		return foodService.food(name);
	}

	@GetMapping("/")
	public FoodDto foods(@RequestParam("id") long id) {
		return foodService.foodById(id);
	}

}
