package com.blogapplication;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.blogapplication.config.AppConstants;
import com.blogapplication.entities.Role;
import com.blogapplication.repositories.RoleRepository;


@SpringBootApplication
public class BlogApplication implements CommandLineRunner{
	
	@Autowired
	RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			
			Role role1 = new Role();
			role1.setId(AppConstants.USER_ROLE_ID);
			role1.setName(AppConstants.ROLE_USER);
			
			Role role2 = new Role();
			role2.setId(AppConstants.ADMIN_ROLE_ID);
			role2.setName(AppConstants.ROLE_ADMIN);
			
			List<Role> roles = List.of(role1 , role2);
			List<Role> saveAll = roleRepository.saveAll(roles);
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
