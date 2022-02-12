/**
 * 
 */
package com.santosh.ms.food.product.service;

import java.util.List;

import com.santosh.ms.food.product.dto.FoodDto;


/**
 * @author santosh.kushwah
 *
 */
public interface FoodService {

	List<FoodDto> food(String name);

	FoodDto foodById(long id);


}
