/**
 * 
 */
package com.santosh.ms.product.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.santosh.ms.product.request.ProductRequestCategoryDto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author santosh.kushwah
 *
 */
@Entity
@Table(name="category")
@Data
@NoArgsConstructor
public class Category {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="category_name")
    private String categoryName;

    public Category(ProductRequestCategoryDto c) {
		this.categoryName = c.getCategoryName();
	}

    public Category(String category) {
        this.categoryName = category;
    }
}
