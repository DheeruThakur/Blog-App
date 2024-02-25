package com.blogapplication.payload;


import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDto {
	
	private int id;
	
	@NotEmpty(message = "name must not be null")
	private String name;
	
	@Email(message="email must be in right format")
	private String email;
	
	@NotEmpty(message = "password must not be null")
	private String password;
	
	@Size(min = 10 , max = 300 , message = "about must be in limited size")
	private String about;
	
	private Set<RoleDto> role = new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public Set<RoleDto> getRole() {
		return role;
	}

	public void setRole(Set<RoleDto> role) {
		this.role = role;
	}

	public UserDto(int id, @NotEmpty(message = "name must not be null") String name,
			@Email(message = "email must be in right format") String email,
			@NotEmpty(message = "password must not be null") String password,
			@Size(min = 10, max = 300, message = "about must be in limited size") String about, Set<RoleDto> role) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
		this.role = role;
	}

	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
