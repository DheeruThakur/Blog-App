package com.blogapplication.payload;



public class CategoryDto {

	private int categoryId;
	
	private String title;
	
	private String categoryDescription;

	public CategoryDto(int categoryId, String title, String categoryDescription) {
		super();
		this.categoryId = categoryId;
		this.title = title;
		this.categoryDescription = categoryDescription;
	}

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

	public CategoryDto() {
		super();
	}
	
	
}
