package com.exam.Service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.hibernate.EmptyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.Repo.CategoryRepository;
import com.exam.Service.CategoryService;
import com.exam.customExceptions.BusinessException;
import com.exam.customExceptions.EmptyUserNameException;
import com.exam.model.quizModel.Category;
@Service
public class CategoryServiceImple implements CategoryService{
	@Autowired
	private CategoryRepository categoryRepository;
	
	

	@Override
	public Category addCategory(Category category) {
		if(category.getTitle().isEmpty() || category.getTitle().length()==0) {
			throw  new EmptyUserNameException("601","Please send proper name , Its blank");
		}
		try {
			Category result1 =  this.categoryRepository.save(category);
			return result1;
			
		}catch (Exception e) {
			// TODO: handle exception
			throw new BusinessException("603","Title is missing pleae fill it");
		}
		
	}

	@Override
	public Category updateCategory(Category category) {
		Category result = this.categoryRepository.save(category);
		
		return result;
	}

	@Override
	public Set<Category> getCategories() {
		
		return new HashSet<>(this.categoryRepository.findAll()); 
		/*
		 * Set<Category> result2 = (Set<Category>)this.categoryRepository.findAll();
		 * return result2;
		 */
	}

	@Override
	public Category getCategory(Long cid) {
		Optional<Category> result3 = this.categoryRepository.findById(cid);
		Category cat = result3.get();
		return cat;
	}

	@Override
	public void deleteCategory(Long cid) {
		this.categoryRepository.deleteById(cid);
		
	}
	
	

}
