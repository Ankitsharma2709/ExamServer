package com.exam.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.model.quizModel.QuizAttempts;

public interface QuizAttemptRepo extends JpaRepository<QuizAttempts, Long>{

}
