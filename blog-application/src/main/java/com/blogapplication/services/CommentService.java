package com.blogapplication.services;

import com.blogapplication.payload.CommentDto;



public interface CommentService {

	// add comment
	CommentDto addComment(CommentDto commentDto , Integer postId);
	
	// delete comment
	void deleteComment(Integer commentId);
}
