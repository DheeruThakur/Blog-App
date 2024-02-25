package com.blogapplication.payload;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PostDto {

	private int postId;

	private String title;

	private String content;

	private String image;

	private Date addedDate;

	private UserDto user;
	
	private Set<CommentDto> comments = new HashSet<>();

	public Set<CommentDto> getComments() {
		return comments;
	}

	public void setComments(Set<CommentDto> comments) {
		this.comments = comments;
	}

	private CategoryDto category;
	
	

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}

	public PostDto(int postId, String title, String content, String image, Date addedDate, UserDto user,
			CategoryDto category) {
		super();
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.image = image;
		this.addedDate = addedDate;
		this.user = user;
		this.category = category;
	}

	public PostDto() {
		super();
	}
}
