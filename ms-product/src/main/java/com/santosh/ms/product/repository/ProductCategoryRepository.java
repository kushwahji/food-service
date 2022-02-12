/**
 * 
 */
package com.santosh.ms.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santosh.ms.product.entity.Category;

/**
 * @author santosh.kushwah
 *
 */
@Repository
public interface ProductCategoryRepository extends JpaRepository<Category, Long> {
    Category findByCategoryName(String category);
}
