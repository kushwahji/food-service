/**
 *
 */
package com.santosh.ms.food.product.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.santosh.ms.food.product.constant.Constant;
import com.santosh.ms.food.product.exception.MsApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.santosh.ms.food.product.dto.FoodDto;
import com.santosh.ms.food.product.entity.Food;
import com.santosh.ms.food.product.repository.FoodRepository;

/**
 * @author santosh.kushwah
 *
 */
@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    FoodRepository foodRepository;

    @Override
    public List<FoodDto> food(@RequestParam("name") String name) {
        List<Food> foods = foodRepository.findByNameContainingOrDescriptionContaining(name, name);
        return Optional.ofNullable(foods).orElseThrow(() -> new MsApplicationException(Constant.FOOD_NOT_FOUND, Constant.FOOD_NOT_FOUND_MSG.replace("FID", name))).stream().map(FoodDto::new).collect(Collectors.toList());
    }

    @Override
    public FoodDto foodById(long id) {
		return new FoodDto(Optional.ofNullable(foodRepository.findById(id).get()).orElseThrow(() -> new MsApplicationException(Constant.FOOD_NOT_FOUND, Constant.FOOD_NOT_FOUND_MSG.replace("FID", ""+id))));
    }
}
