package com.exam.Service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.Repo.QuizAttemptRepo;
import com.exam.Repo.UserReposiroty;
import com.exam.Service.QuizAttemptService;
import com.exam.model.quizModel.QuizAttempts;

@Service
public class QuizAttemptServiceImpl implements QuizAttemptService{
	@Autowired
	private QuizAttemptRepo quizAttemptRepository;
	
	
	@Autowired
	private UserReposiroty userRepo;

	@Override
	public QuizAttempts submitQuiz(QuizAttempts quizAttempt)  {
		// TODO Auto-generated method stub
		quizAttempt.setTimeStamp(LocalDateTime.now());
		return quizAttemptRepository.save(quizAttempt);
		
	}

	@Override
	public QuizAttempts updateQuiz(QuizAttempts quizAttempt) {
		// TODO Auto-generated method stub
		return quizAttemptRepository.save(quizAttempt);
	}

	@Override
	public void deleteQuizAttempts(Long attemptId) {
		
		this.quizAttemptRepository.deleteById(attemptId);
		
	}

	@Override
	public QuizAttempts getQuizAttempts(Long attemptId) {
		// TODO Auto-generated method stub
		Optional<QuizAttempts> quiz = this.quizAttemptRepository.findById(attemptId);
		QuizAttempts results = quiz.get();
		return results;
	}

	@Override
	public List<QuizAttempts> getAllQuizAttempts() {
		// TODO Auto-generated method stub
		return this.quizAttemptRepository.findAll();
	}
	
	
	
	

}
