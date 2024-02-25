package com.blogapplication.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;



@Entity
public class Post {

	@Id
	@GeneratedValue(generator = "post_seq")
	@SequenceGenerator(name = "post_seq", initialValue = 10001, allocationSize = 1)
	private int postId;

	private String title;

	private String content;

	private String image;

	private Date addedDate;

	@ManyToOne
	private User user;

	@ManyToOne
	private Category category;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private Set<Comment> comments = new HashSet<>();

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Post(int postId, String title, String content, String image, Date addedDate, User user, Category category) {
		super();
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.image = image;
		this.addedDate = addedDate;
		this.user = user;
		this.category = category;
	}

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

}
