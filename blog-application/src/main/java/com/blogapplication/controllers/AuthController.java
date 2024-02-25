package com.blogapplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapplication.exceptions.ApiException;
import com.blogapplication.payload.UserDto;
import com.blogapplication.security.JwtAuthRequest;
import com.blogapplication.security.JwtAuthResponse;
import com.blogapplication.security.JwtTokenHelper;
import com.blogapplication.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	UserDetailsService userDetailsService;

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken (@RequestBody JwtAuthRequest request) throws Exception{
		authenticate(request.getUsername(), request.getPassword());
		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		String token = jwtTokenHelper.generateToken(userDetails);
		return new ResponseEntity<>(new JwtAuthResponse(token) , HttpStatus.OK );
	}
	
	private void authenticate(String username , String password) throws Exception {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		
		try {
			
			authenticationManager.authenticate(authenticationToken);
			
		} catch (BadCredentialsException e) {
			System.out.println("Invalid credential !!");
			throw new ApiException("Invalid username or password");
		}
	}
	
	// register new user
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser (@RequestBody UserDto userDto){
		UserDto registerUser = userService.registerUser(userDto);
		return new ResponseEntity<>(registerUser , HttpStatus.CREATED);
	}
}
