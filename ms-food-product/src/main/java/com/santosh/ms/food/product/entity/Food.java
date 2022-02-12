/**
 * 
 */
package com.santosh.ms.food.product.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author santosh.kushwah
 * @since 27-01-2022
 */
@Data
@Entity
@Table(name = "food")
public class Food {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long fid;
	private Long vendorId;
	private String name;
	private String description;
	private BigDecimal price;
}
