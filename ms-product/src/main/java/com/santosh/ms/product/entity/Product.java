package com.santosh.ms.product.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.santosh.ms.product.request.ProductRequestDto;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
/**
 * @author santosh.kushwah
 * @since 12-01-2022
 */

@Entity
@Table(name="product")
@Data
@NoArgsConstructor
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;

    @Column(name ="name")
    private String name;
    
    @Column(name ="sku")
    private String sku;

    @Column(name ="description")
    private String description;

    @Column(name ="price")
    private BigDecimal price;

    @Column(name ="image_url")
    private String imageUrl;

    @Column(name ="active")
    private boolean active;

    @Column(name ="stock")
    private int stock;

    @Column(name ="date_created")
    @CreationTimestamp
    private Date dateCreated;

    @Column(name ="last_updated")
    @UpdateTimestamp
    private Date lastUpdated;

    @Column(name ="category_id")
    private long categoryId;

    public Product(ProductRequestDto p, Long categoryId) {
		this.name = p.getName();
		this.sku = p.getSku();
		this.description = p.getDescription();
		this.price = p.getPrice();
		this.imageUrl = p.getImageUrl();
		this.active = p.isActive();
		this.stock = p.getStock();
		this.categoryId = categoryId;
	}
}