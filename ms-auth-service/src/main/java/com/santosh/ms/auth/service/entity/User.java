/**
 * 
 */
package com.santosh.ms.auth.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.santosh.ms.auth.service.request.UserRegisterDto;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author santosh.kushwah
 * @since 11-02-2022
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;

	@Column(name = "age")
	private int age;

	@Column(name = "phone_number")
	private String phone;

	@Column(name = "email")
	private String email;
	
	public User(UserRegisterDto request) {
		super();
		this.username = request.getUsername();
		this.password = request.getPassword();
		this.firstName = request.getFirstName();
		this.lastName = request.getLastName();
		this.age = request.getAge();
		this.phone = request.getPhone();
		this.email = request.getEmail();
	}
}
