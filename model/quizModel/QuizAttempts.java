package com.exam.model.quizModel;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.exam.model.User;
@Entity
public class QuizAttempts {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long attemptId;
	private LocalDateTime timeStamp;
	private int correctAnswers;
	private int totalQuestions;
	private Long userId;
	private Long quizId;
	public Long getAttemptId() {
		return attemptId;
	}
	public void setAttemptId(Long attemptId) {
		this.attemptId = attemptId;
	}
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	public int getCorrectAnswers() {
		return correctAnswers;
	}
	public void setCorrectAnswers(int correctAnswers) {
		this.correctAnswers = correctAnswers;
	}
	public int getTotalQuestions() {
		return totalQuestions;
	}
	public void setTotalQuestions(int totalQuestions) {
		this.totalQuestions = totalQuestions;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getQuizId() {
		return quizId;
	}
	public void setQuizId(Long quizId) {
		this.quizId = quizId;
	}
	public QuizAttempts(Long attemptId, LocalDateTime timeStamp, int correctAnswers, int totalQuestions, Long userId,
			Long quizId) {
		super();
		this.attemptId = attemptId;
		this.timeStamp = timeStamp;
		this.correctAnswers = correctAnswers;
		this.totalQuestions = totalQuestions;
		this.userId = userId;
		this.quizId = quizId;
	}
	public QuizAttempts() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

	
	
	

}
