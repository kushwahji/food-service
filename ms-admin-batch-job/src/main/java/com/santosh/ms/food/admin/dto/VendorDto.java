/**
 * 
 */
package com.santosh.ms.food.admin.dto;

import com.santosh.ms.food.admin.entity.Vendor;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author santosh.kushwah
 *
 */
@Data
@NoArgsConstructor
public class VendorDto {
	public VendorDto(Vendor v) {
		this.name = v.getName();
		this.description = v.getDescription();
	}

	private String name;
	private String description;
}
