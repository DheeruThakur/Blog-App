package com.blogapplication.services;

import java.util.List;

import com.blogapplication.payload.CategoryDto;



public interface CategoryService {

	// create
	CategoryDto addCategory(CategoryDto categoryDto);
	// update
	CategoryDto updateCategory(CategoryDto categoryDto , Integer categoryId);
	// delete
	void deleteCategory(Integer categoryId);
	//get
	CategoryDto getCategoryById(Integer categoryId);
	//getAll
	List<CategoryDto> getAllCategory();
}
