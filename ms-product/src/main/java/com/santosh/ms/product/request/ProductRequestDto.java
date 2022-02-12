package com.santosh.ms.product.request;

import com.santosh.ms.product.entity.Category;
import com.santosh.ms.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author santosh.kushwah
 * @since 12-01-2022
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequestDto {
    private String name;
    private String sku;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private boolean active;
    private int stock;
    private String category;

    public ProductRequestDto(Product product , String category) {
        this.name = product.getName();
        this.sku = product.getSku();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.imageUrl = product.getImageUrl();
        this.active = product.isActive();
        this.stock = product.getStock();
        this.category = category;
    }
}