/**
 * 
 */
package com.santosh.ms.auth.service.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.santosh.ms.auth.service.entity.User;
import com.santosh.ms.auth.service.repository.UserRepository;
import com.santosh.ms.auth.service.request.UserLoginDto;
import com.santosh.ms.auth.service.request.UserRegisterDto;
import com.santosh.ms.auth.service.response.ApiResponse;
import com.santosh.ms.auth.service.util.JwtUtil;

/**
 * @author santosh.kushwah
 * @since 11-02-2022
 */
@Service
public class AuthServiceImpl implements IAuthService, UserDetailsService {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	UserRepository userRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private BCryptPasswordEncoder bCryptEncoder;

	@Override
	public Object login(UserLoginDto request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		final User user = Optional.ofNullable(userRepository.findByUsername(request.getUsername())).orElseThrow(()->new UsernameNotFoundException("Invalid username or password."));
		final String token = jwtUtil.generateToken(user.getUsername());
		return new ApiResponse(token, "Token generated successfully!");
	}

	@Override
	public Object register(UserRegisterDto request) {
		request.setPassword(bCryptEncoder.encode(request.getPassword()));
		return userRepository.save(new User(request));
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		Optional.ofNullable(user).orElseThrow(()->new UsernameNotFoundException("Invalid username or password."));
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				getAuthority());
	}
	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

}
