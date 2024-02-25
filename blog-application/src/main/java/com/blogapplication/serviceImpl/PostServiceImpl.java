package com.blogapplication.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blogapplication.entities.Category;
import com.blogapplication.entities.Post;
import com.blogapplication.entities.User;
import com.blogapplication.exceptions.ResourceNotFoundException;
import com.blogapplication.payload.PostDto;
import com.blogapplication.payload.PostResponse;
import com.blogapplication.repositories.CategoryRepository;
import com.blogapplication.repositories.PostRepository;
import com.blogapplication.repositories.UserRepository;
import com.blogapplication.services.PostService;


@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDto addPost(PostDto postDto, Integer userId, Integer categoryId) {
		Post post = modelMapper.map(postDto, Post.class);
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));
		post.setUser(user);
		post.setCategory(category);
		post.setAddedDate(new Date());
		Post savedPost = postRepository.save(post);
		return modelMapper.map(savedPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));
		post.setTitle(postDto.getTitle());
		post.setImage(postDto.getImage());
		post.setContent(postDto.getContent());
		Post savedPost = postRepository.save(post);
		return modelMapper.map(savedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));
		postRepository.delete(post);
	}

	@Override
	public PostDto getPost(Integer postId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostResponse getAllPosts(Integer pageSize, Integer pageNumber, String sortBy, String sortDir) {

		Sort sort = null;
		sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		Pageable pg = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePost = postRepository.findAll(pg);
		List<Post> post = pagePost.getContent();
		List<PostDto> listDto = post.stream().map(p -> modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
		PostResponse postRes = new PostResponse();
		postRes.setContent(listDto);
		postRes.setLastPage(pagePost.isLast());
		postRes.setPageNumber(pagePost.getNumber());
		postRes.setPageSize(pagePost.getSize());
		postRes.setTotalElements(pagePost.getTotalElements());
		postRes.setTotalPages(pagePost.getTotalPages());
		return postRes;
	}

	@Override
	public List<PostDto> getAllPostsByCategory(Integer categoryId) {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));
		List<Post> post = postRepository.findByCategory(category);
		List<PostDto> listDto = post.stream().map(p -> modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
		return listDto;
	}

	@Override
	public List<PostDto> getAllPostsByUser(Integer userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));
		List<Post> post = postRepository.findByUser(user);
		List<PostDto> listDto = post.stream().map(p -> modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
		return listDto;
	}

	@Override
	public List<PostDto> getAllPostsByKeyword(String keyword) {
		List<Post> post = postRepository.findByTitleContaining(keyword);
		List<PostDto> listDto = post.stream().map(p -> modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
		return listDto;
	}

}
