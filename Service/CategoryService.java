package com.exam.Service;

import java.util.Set;

import com.exam.model.quizModel.Category;

public interface CategoryService {
	public Category addCategory(Category category);
	public Category updateCategory(Category category);
	public Set<Category> getCategories();
	public Category getCategory(Long cid);
	public void deleteCategory(Long cid);

}
