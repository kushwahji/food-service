/**
 * This class use for user registration
 */
package com.santosh.ms.auth.service.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;

/**
 * @author santosh.kushwah
 * @since 11-02-2022
 */
@Data
public class UserRegisterDto {

	@NotNull(message = "username must not be null")
	private String username;

	@NotNull(message = "password must not be null")
	private String password;

	@NotNull(message = "firstname must not be null")
	private String firstName;

	@NotNull(message = "lastname must not be null")
	private String lastName;

	@Min(value = 1)
	private int age;

	@NotNull(message = "phone must not be null")
	@Pattern(regexp = "\\d{10}$",message = "phone should be only 10 digits.")
	private String phone;
	@NotNull
	@Email
	private String email;
}
