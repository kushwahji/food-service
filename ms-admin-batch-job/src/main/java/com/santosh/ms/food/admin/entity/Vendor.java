/**
 * 
 */
package com.santosh.ms.food.admin.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.santosh.ms.food.admin.dto.VendorDto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author santosh.kushwah
 *
 */
@Data
@Entity
@Table(name = "vendor")
@NoArgsConstructor
public class Vendor {
	public Vendor(VendorDto request) {
		this.name = request.getName();
		this.description = request.getDescription();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vendorId;
	private String name;
	private String description;
}
