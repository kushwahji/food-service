/**
 * 
 */
package com.santosh.ms.product.service;

import java.util.List;

import com.santosh.ms.product.entity.Category;
import com.santosh.ms.product.request.ProductRequestCategoryDto;
import com.santosh.ms.product.request.ProductRequestDto;

/**
 * @author santosh.kushwah
 *
 */
public interface AdminProductService {

	List<ProductRequestDto> addProduct(List<ProductRequestDto> product);

	List<Category> addCategory(List<ProductRequestCategoryDto> product);

}
