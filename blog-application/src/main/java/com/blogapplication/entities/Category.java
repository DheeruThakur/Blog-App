package com.blogapplication.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;



@Entity
public class Category {

	@Id
	@GeneratedValue(generator = "category_seq")
	@SequenceGenerator(name = "category_seq", initialValue = 1001, allocationSize = 1)
	private int categoryId;
	
	private String title;
	
	private String categoryDescription;
	
	@OneToMany(mappedBy = "category" , cascade = CascadeType.ALL) /// yaha gadbad ho sacti hai(mapped by me)
	private List<Post> posts = new ArrayList<>();

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Category(int categoryId, String title, String categoryDescription, List<Post> posts) {
		super();
		this.categoryId = categoryId;
		this.title = title;
		this.categoryDescription = categoryDescription;
		this.posts = posts;
	}

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

		
	
}
