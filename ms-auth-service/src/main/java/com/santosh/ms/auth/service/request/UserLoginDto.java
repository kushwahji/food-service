/**
 * 
 */
package com.santosh.ms.auth.service.request;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author santosh.kushwah
 * @since 11-02-2022
 */
@Data
public class UserLoginDto {
	
	@NotNull(message = "username must not be null")
	private String username;
	
	@NotNull(message = "password must not be null")
	private String password;
}
