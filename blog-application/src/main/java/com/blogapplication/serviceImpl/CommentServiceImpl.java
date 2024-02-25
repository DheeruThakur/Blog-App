package com.blogapplication.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapplication.entities.Comment;
import com.blogapplication.entities.Post;
import com.blogapplication.exceptions.ResourceNotFoundException;
import com.blogapplication.payload.CommentDto;
import com.blogapplication.repositories.CommentRepository;
import com.blogapplication.repositories.PostRepository;
import com.blogapplication.services.CommentService;



@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public CommentDto addComment(CommentDto commentDto, Integer postId) {
		Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		Comment comment = modelMapper.map(commentDto , Comment.class);
		comment.setPost(post);
		Comment savedComment = commentRepository.save(comment);
		return modelMapper.map(savedComment, CommentDto.class);
	}

	
	public void deleteComment(Integer commentId) {
		Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));
		commentRepository.delete(comment);
	}

}
