package com.santosh.ms.auth.service.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.santosh.ms.auth.service.request.UserLoginDto;
import com.santosh.ms.auth.service.request.UserRegisterDto;
import com.santosh.ms.auth.service.service.IAuthService;

@RestController
public class AuthRestController {
	
	@Autowired
	IAuthService authService;

	@PostMapping("/auth/token")
	public ResponseEntity<Object> login(@RequestBody @Valid UserLoginDto request) {
		return new ResponseEntity<>(authService.login(request), HttpStatus.OK);
	}

	@PostMapping("/auth/register")
	public ResponseEntity<Object> register(@RequestBody @Valid UserRegisterDto request) {
		return new ResponseEntity<>(authService.register(request), HttpStatus.OK);
	}
}
