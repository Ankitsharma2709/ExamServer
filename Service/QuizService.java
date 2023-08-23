package com.exam.Service;

import java.util.List;
import java.util.Set;

import com.exam.model.quizModel.Category;
import com.exam.model.quizModel.Quiz;

public interface QuizService {
	public Quiz addQuiz(Quiz quiz);
	public Quiz updateQuiz(Quiz quiz);
	public Set<Quiz> getQuiz();
	public Quiz getQuizById(Long qId);
	public void deleteQuiz(Long qid);
	public List<Quiz> getQuizzesOfCategory(Category c);
	public List<Quiz> getActiveQuizzes();
	public List<Quiz> getActiveQuizzesOfCategory(Category c);

}
