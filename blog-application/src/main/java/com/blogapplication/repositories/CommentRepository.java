package com.blogapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapplication.entities.Comment;




public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
