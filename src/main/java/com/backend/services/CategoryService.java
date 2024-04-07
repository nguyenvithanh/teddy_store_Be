package com.backend.services;

import java.util.List;

import com.backend.model.Category;

public interface CategoryService {

	public List<Category> getAllCategory();
	
	Object getAllCategory(int page, int size);

	Object updateCategory(String id, String name, Boolean active);

	Object searchCategory(String name);

	Object getAllCategoryActive();
}
