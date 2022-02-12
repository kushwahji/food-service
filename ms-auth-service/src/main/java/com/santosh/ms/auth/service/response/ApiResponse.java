/**
 * 
 */
package com.santosh.ms.auth.service.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author santosh.kushwah
 * @since 11-02-2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
	private String token;
	private String message;
}
