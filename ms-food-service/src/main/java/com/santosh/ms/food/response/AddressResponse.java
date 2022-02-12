/**
 * 
 */
package com.santosh.ms.food.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author santosh.kushwah
 * @since 12-01-2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {
	
	private String address;

	private String landmark;

	private String city;

	private String state;

	private String country;

	private int pincode;
	
	private boolean defaults;

}
