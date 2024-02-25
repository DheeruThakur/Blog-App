package com.blogapplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapplication.payload.ApiResponse;
import com.blogapplication.payload.CommentDto;
import com.blogapplication.services.CommentService;



@RestController
@RequestMapping("/api")
public class CommentController {
	
	@Autowired
	private CommentService commentService;

	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto , @PathVariable Integer postId){
		CommentDto comment = commentService.addComment(commentDto, postId);
		return new ResponseEntity<CommentDto>(comment , HttpStatus.CREATED);
	}
	
	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<ApiResponse> createComment(@PathVariable Integer commentId){
		commentService.deleteComment(commentId);
		return ResponseEntity.ok(new ApiResponse("comment deleted successfully" , true));
	}
}
