package com.blogapplication.serviceImpl;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogapplication.config.AppConstants;
import com.blogapplication.entities.Role;
import com.blogapplication.entities.User;
import com.blogapplication.exceptions.ResourceNotFoundException;
import com.blogapplication.payload.UserDto;
import com.blogapplication.repositories.RoleRepository;
import com.blogapplication.repositories.UserRepository;
import com.blogapplication.services.UserService;



@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDto createUser(UserDto userDto , String role) { 
		User user = modelMapper.map(userDto, User.class);
		Role role1 = new Role();
		
		if(role.equalsIgnoreCase(AppConstants.ROLE_USER)) { 
			role1 = roleRepository.findById(AppConstants.USER_ROLE_ID).get();
		}
		
		else if(role.equalsIgnoreCase(AppConstants.ROLE_USER)) {
			role1 = roleRepository.findById(AppConstants.ADMIN_ROLE_ID).get();
		}
		
		user.getRoles().add(role1);
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		
		User savedUser = userRepository.save(user);
		return modelMapper.map(savedUser , UserDto.class);
	}

	@Override
	public UserDto updateUser(UserDto userDto, int userId) {
		User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user" , "userId" , userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User savedUser = userRepository.save(user);
		return modelMapper.map(savedUser, UserDto.class);
	}

	@Override
	public UserDto getUserById(int userId) {
		User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user" , "userId" , userId));
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = userRepository.findAll();
		List<UserDto> userList = users.stream().map(u -> modelMapper.map(u, UserDto.class)).collect(Collectors.toList());
		return userList;
	}

	@Override
	public void deleteUser(int userId) {
		User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user" , "userId" , userId));
		userRepository.delete(user);
	}

	@Override
	public UserDto registerUser(UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		Role role = roleRepository.findById(AppConstants.USER_ROLE_ID).get();
		user.getRoles().add(role);
		User save = userRepository.save(user);
		return modelMapper.map(save, UserDto.class);
	}

}
