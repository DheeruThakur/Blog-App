package com.blogapplication.services;

import java.util.List;

import com.blogapplication.payload.PostDto;
import com.blogapplication.payload.PostResponse;



public interface PostService {

	PostDto addPost(PostDto postDto, Integer userId, Integer categoryId);

	PostDto updatePost(PostDto postDto, Integer postId);

	void deletePost(Integer postId);

	PostDto getPost(Integer postId);

	PostResponse getAllPosts(Integer pageSize , Integer pageNumber, String sortBy, String sortDir);

	List<PostDto> getAllPostsByCategory(Integer categoryId);

	List<PostDto> getAllPostsByUser(Integer userId);

	List<PostDto> getAllPostsByKeyword(String keyword);
}
