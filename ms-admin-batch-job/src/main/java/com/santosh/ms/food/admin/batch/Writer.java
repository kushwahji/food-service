package com.santosh.ms.food.admin.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.santosh.ms.food.admin.batch.repository.FoodRepository;
import com.santosh.ms.food.admin.entity.Food;


@Component
public class Writer implements ItemWriter<Food>{
	
	@Autowired
	private FoodRepository repo;

	@Override
	@Transactional
	public void write(List<? extends Food> foods) throws Exception {
		repo.saveAll(foods);
	}
	
}
