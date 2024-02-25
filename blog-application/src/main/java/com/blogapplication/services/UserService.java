package com.blogapplication.services;

import java.util.List;

import com.blogapplication.payload.UserDto;



public interface UserService {

	UserDto registerUser(UserDto userDto);
	UserDto createUser(UserDto userDto, String role);
	UserDto updateUser(UserDto userDto , int userId);
	UserDto getUserById(int userId);
	List<UserDto> getAllUsers();
	void deleteUser(int userId);
}
