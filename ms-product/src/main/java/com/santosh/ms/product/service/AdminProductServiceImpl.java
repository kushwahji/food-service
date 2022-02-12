/**
 *
 */
package com.santosh.ms.product.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.santosh.ms.product.exception.MsApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.santosh.ms.product.entity.Product;
import com.santosh.ms.product.entity.Category;
import com.santosh.ms.product.repository.ProductCategoryRepository;
import com.santosh.ms.product.repository.ProductRepository;
import com.santosh.ms.product.request.ProductRequestCategoryDto;
import com.santosh.ms.product.request.ProductRequestDto;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author santosh.kushwah
 * @since 14-01-2022
 */
@Service
public class AdminProductServiceImpl implements AdminProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Transactional
    @Override
    public List<ProductRequestDto> addProduct(List<ProductRequestDto> product) {
        return product.stream().filter(f -> getCategory(f.getCategory()).getCategoryName().equals(f.getCategory())).
                map(p -> {
                    Category cat = getCategory(p.getCategory());
                    Product prod = productRepository.save(new Product(p, cat.getId()));
                    return new ProductRequestDto(prod, cat.getCategoryName());
                }).collect(Collectors.toList());
    }

    @Override
    public List<Category> addCategory(List<ProductRequestCategoryDto> productCategory) {
        return productCategory.stream().map(p -> productCategoryRepository.save(new Category(p))).collect(Collectors.toList());
    }

    public Category getCategory(String category) {
        return Optional.ofNullable(productCategoryRepository.findByCategoryName(category)).orElseThrow(() -> new MsApplicationException("data not found","Please select right category or add new category"));
    }
}
