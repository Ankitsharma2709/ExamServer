package com.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.Service.QuizAttemptService;
import com.exam.model.quizModel.QuizAttempts;

@RestController
@RequestMapping("/quiz-attempt")
@CrossOrigin("*")
public class QuizAttemotController {
	@Autowired
	private QuizAttemptService quizAttemptService;
	
	@PostMapping("/submit")
	public ResponseEntity<?> submitQuizAttempt(@RequestBody QuizAttempts quizAttempt){
		try {
			 QuizAttempts submittedQuizAttempts = quizAttemptService.submitQuiz(quizAttempt);
			 return ResponseEntity.ok(submittedQuizAttempts);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error submitting the quiz");
			
		}
		
	}	
	
	@GetMapping("/data")
	public ResponseEntity<?> getQuizAttemptData(){
		return ResponseEntity.ok(this.quizAttemptService.getAllQuizAttempts());
	}
	
	
	
	
	 
	
	
  
}
