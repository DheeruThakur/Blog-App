package com.blogapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blogapplication.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

	@Query("SELECT r FROM Role r WHERE upper(r.name) = upper(:role)")
	Role findByName(@Param("role") String role);

}
