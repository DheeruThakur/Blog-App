package com.blogapplication.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blogapplication.config.AppConstants;
import com.blogapplication.payload.ApiResponse;
import com.blogapplication.payload.PostDto;
import com.blogapplication.payload.PostResponse;
import com.blogapplication.services.FileService;
import com.blogapplication.services.PostService;

import jakarta.servlet.http.HttpServletResponse;



@RestController
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	// "application.properties" wala path use hoga
	@Value("${project.image}")
	private String path;
	
	// to create post
	@PostMapping("/user/{userId}/category/{categoryId}")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto , @PathVariable Integer userId , @PathVariable Integer categoryId){
		PostDto post = postService.addPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(post , HttpStatus.CREATED);
	}
	
	// to update post
	@PutMapping("/update/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto , @PathVariable Integer postId){
		PostDto post = postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(post , HttpStatus.CREATED);
	}
	
	// to delete post
	@DeleteMapping("/delete/{postId}")
	public ResponseEntity<ApiResponse> removePost(@PathVariable Integer postId){
		postService.deletePost(postId);
		return ResponseEntity.ok(new ApiResponse("post deleted successfully" , true));
	}
	
	// get single post
	@GetMapping("/{postId}")
	public ResponseEntity<PostDto> fetchPost(@PathVariable Integer postId){
		PostDto postDto = postService.getPost(postId);
		return new ResponseEntity<PostDto>(postDto , HttpStatus.OK);
	}
	
	// get all post
	@GetMapping("/")
	public ResponseEntity<PostResponse> fetchAllPost(@RequestParam(defaultValue = AppConstants.PAGE_SIZE , required = false) Integer pageSize , @RequestParam(defaultValue = AppConstants.PAGE_NUMBER , required = false) Integer pageNumber , @RequestParam(defaultValue = AppConstants.SORT_BY , required = false) String sortBy , @RequestParam(defaultValue = AppConstants.SORT_DIR , required = false) String sortDir){
		PostResponse postDto = postService.getAllPosts(pageSize , pageNumber , sortBy , sortDir);
		return new ResponseEntity<PostResponse>(postDto , HttpStatus.OK);
	}
	
	// get posts by category
	@GetMapping("/search/category/{categoryId}")
	public ResponseEntity<List<PostDto>> fetchAllPostsByCategory(@PathVariable Integer categoryId){
		List<PostDto> postDto = postService.getAllPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(postDto , HttpStatus.OK);
	}
	
	// get posts by user
	@GetMapping("/search/user/{userId}")
	public ResponseEntity<List<PostDto>> fetchAllPostsByUser(@PathVariable Integer userId){
		List<PostDto> postDto = postService.getAllPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(postDto , HttpStatus.OK);
	}
	
	// get post by keyword
	@GetMapping("/search/title/{keyword}")
	public ResponseEntity<List<PostDto>> fetchAllPostsByKeyword(@PathVariable String keyword){
		List<PostDto> postDto = postService.getAllPostsByKeyword(keyword);
		return new ResponseEntity<List<PostDto>>(postDto , HttpStatus.OK);
	}
	
	// to upload image
	@PostMapping("image/upload/{postId}")
	public ResponseEntity<PostDto> uploadImage(@RequestParam("image") MultipartFile image , @PathVariable int postId) throws IOException{
		
		PostDto postDto = postService.getPost(postId);
		String uploadedImage = fileService.uplaodImage(path , image);
		postDto.setImage(uploadedImage);
		PostDto updatePost = postService.updatePost(postDto , postId);
		
		return new ResponseEntity<>(updatePost , HttpStatus.OK);
	}
	
	// to download/serve image
	@GetMapping(value = "/image/{imageName}" , produces = MediaType.IMAGE_JPEG_VALUE )
	public void downloadImage(@PathVariable("imageName") String imageName , HttpServletResponse response) throws IOException{
		InputStream resource = fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
}
