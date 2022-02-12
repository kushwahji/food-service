/**
 * 
 */
package com.santosh.ms.food.admin.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santosh.ms.food.admin.entity.Food;

/**
 * @author santosh.kushwah
 *
 */
@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

}
