package com.exam.Service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.Repo.QuizRepository;
import com.exam.Service.QuizService;
import com.exam.model.quizModel.Category;
import com.exam.model.quizModel.Quiz;
@Service
public class QuizServiceImpl implements QuizService{
	@Autowired
	private QuizRepository quizRepository;
	

	@Override
	public Quiz addQuiz(Quiz quiz) {
		
		
		return this.quizRepository.save(quiz);
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
	
		return this.quizRepository.save(quiz);
	}

	@Override
	public Set<Quiz> getQuiz() {
		return new HashSet<>(this.quizRepository.findAll()); 
		/*
		 * Set<Quiz> quis = (Set<Quiz>)this.quizRepository.findAll(); return quis;
		 */
	}

	@Override 
	public Quiz getQuizById(Long qId) {
		Optional<Quiz> less = this.quizRepository.findById(qId);
		Quiz que = less.get();
		return que;
	}

	@Override
	public void deleteQuiz(Long qid) {
		/*
		 * try { quizRepository.deleteById(qid);
		 * 
		 * } catch (Exception e) { // TODO: handle exception e.printStackTrace(); }
		 */
//		Quiz quiz = new Quiz();
//		quiz.setQid(qid);
		this.quizRepository.deleteById(qid);
		
//		quizRepository.deleteById(qid);
		
	}
	
	//getting quizzes of particular category

	@Override
	public List<Quiz> getQuizzesOfCategory(Category c) {
		return this.quizRepository.findBycategory(c);
	}
	//get active quizzes

	@Override
	public List<Quiz> getActiveQuizzes() {
		return this.quizRepository.findByActive(true);
		
	}

	@Override
	public List<Quiz> getActiveQuizzesOfCategory(Category c) {
		return this.quizRepository.findByCategoryAndActive(c, true);
	}

}
