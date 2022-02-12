package com.santosh.ms.food.admin.batch;

import java.util.Optional;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.santosh.ms.food.admin.entity.Food;
import com.santosh.ms.food.admin.entity.Vendor;
import com.santosh.ms.food.admin.repository.VendorRepository;

@Component
public class Processor implements ItemProcessor<Food, Food> {

	@Autowired
	private VendorRepository vendorRepository;

	@Override
	public Food process(Food food) throws Exception {
		Optional<Vendor> v = vendorRepository.findById(food.getVendorId());
		if (v.isPresent()) {
			return food;
		}
		return null;
	}

}
