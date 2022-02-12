/**
 *
 */
package com.santosh.ms.product.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.santosh.ms.product.constant.Constant;
import com.santosh.ms.product.entity.Product;
import com.santosh.ms.product.exception.MsApplicationException;
import com.santosh.ms.product.repository.ProductRepository;
import com.santosh.ms.product.response.ProductResponseDto;

/**
 * @author santosh.kushwah
 * @since 12-01-2022
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<ProductResponseDto> search(String title) {
        return Optional.ofNullable(productRepository.findByNameContainingOrDescriptionContaining(title, title)).filter(f->f.size()>0).orElseThrow(() -> new MsApplicationException(Constant.PRODUCT_NOT_FOUND, Constant.PRODUCT_NOT_FOUND_MSG.replace("ID", title))).stream().map(ProductResponseDto::new).collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        return Optional.ofNullable(productRepository.findAll()).filter(f->f.size()>0).orElseThrow(() -> new MsApplicationException(Constant.PRODUCT_NOT_FOUND, Constant.PRODUCT_NOT_FOUND)).stream().map(ProductResponseDto::new).collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto getProduct(long productId) {
		return new ProductResponseDto(Optional.ofNullable(productRepository.findById(productId).get()).orElseThrow(() -> new MsApplicationException(Constant.PRODUCT_NOT_FOUND, Constant.PRODUCT_NOT_FOUND_MSG.replace("ID", "" + productId))));
    }

}
