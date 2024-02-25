package com.blogapplication.controllers;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogapplication.config.AppConstants;
import com.blogapplication.payload.ApiResponse;
import com.blogapplication.payload.UserDto;
import com.blogapplication.services.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/create-user")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto , @RequestParam String role){
		UserDto user = userService.createUser(userDto , role);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	
	@PostMapping("/update-user/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto , @PathVariable Integer userId){
		UserDto user = userService.updateUser(userDto , userId);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/get-user/{userId}")
	public ResponseEntity<UserDto> searchUser(@PathVariable Integer userId){
		UserDto user = userService.getUserById(userId);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/get-all-user")
	public ResponseEntity<List<UserDto>> fetchAllUser(){
		List<UserDto> user = userService.getAllUsers();
		return ResponseEntity.ok(user);
	}
	
	@DeleteMapping("/delete-user/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable int userId){
		userService.deleteUser(userId);
		return ResponseEntity.ok(new ApiResponse("user deleted successfully" , true));
	}
}
