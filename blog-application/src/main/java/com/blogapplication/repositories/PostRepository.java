package com.blogapplication.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.blogapplication.entities.Category;
import com.blogapplication.entities.Post;
import com.blogapplication.entities.User;


public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findByCategory(Category category);

	List<Post> findByUser(User user);

	List<Post> findByTitleContaining(String keyword);

}
