package com.exam.Service;

import java.util.List;

import com.exam.model.quizModel.QuizAttempts;

public interface QuizAttemptService {
	public QuizAttempts submitQuiz(QuizAttempts quizAttempt);
	public QuizAttempts updateQuiz(QuizAttempts quizAttempt);
	public void deleteQuizAttempts(Long attemptId);                 
	public QuizAttempts getQuizAttempts(Long attemptId);
	public List<QuizAttempts>getAllQuizAttempts();
	
	

}
 