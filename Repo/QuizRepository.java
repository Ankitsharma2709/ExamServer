package com.exam.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.model.quizModel.Category;
import com.exam.model.quizModel.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long>{
	public List<Quiz> findBycategory(Category c);
	
	public List<Quiz> findByActive(Boolean b);
	
	public List<Quiz> findByCategoryAndActive(Category c , Boolean b);
	

}
