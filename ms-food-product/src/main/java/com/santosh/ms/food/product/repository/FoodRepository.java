package com.santosh.ms.food.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.santosh.ms.food.product.entity.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
	List<Food> findByNameContainingOrDescriptionContaining(String title, String title2);
}
