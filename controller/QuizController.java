package com.exam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.Service.QuizService;
import com.exam.model.quizModel.Category;
import com.exam.model.quizModel.Quiz;

import javassist.NotFoundException;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {
	@Autowired
	private QuizService quizService;
	
	//add the quiz
	@PostMapping("/")
	public ResponseEntity<?> addQuiz(@RequestBody Quiz quiz){
		try {
			Quiz quiz1 = this.quizService.addQuiz(quiz);
			return ResponseEntity.ok(quiz1);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			// TODO: handle exception
		}
	}
	
	//getting all the questions
	@GetMapping("/")
	public ResponseEntity<?> getQuizes(){
		return ResponseEntity.ok(this.quizService.getQuiz());
	}
	
	//get the quizes by id
	@GetMapping("/{qid}")
	public Quiz getQuizById(@PathVariable("qid") Long qid) throws NotFoundException   {
//		
		return this.quizService.getQuizById(qid);
	}
	
	//update the quizzes
	@PutMapping("/")
	public Quiz updateQuizes(@RequestBody Quiz quiz) throws NotFoundException{
		
			return this.quizService.updateQuiz(quiz);
			
		
		
	}
	
	//delete handler
	@DeleteMapping("/{qid}")
	public void deleteQuizes(@PathVariable("qid") Long qid) throws NotFoundException{
		this.quizService.deleteQuiz(qid);
	}
	//getting particualr quiz
	@GetMapping("/category/{cid}")
	public ResponseEntity<?> getQuizOfCategory(@PathVariable("cid") Long cid){
		try {
			Category c = new Category();
			c.setCid(cid);
			return ResponseEntity.ok(this.quizService.getQuizzesOfCategory(c));
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			// TODO: handle exception
		}
	}
	
	//get active quizzes
	@GetMapping("/active")
	public ResponseEntity<?> getActiveQuizzes(){
		try {
			return ResponseEntity.ok(this.quizService.getActiveQuizzes());
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			// TODO: handle exception
		}
	}
	
	@GetMapping("/category/active/{cid}")
	public ResponseEntity<?> getActiveQuizzesOfCategory(@PathVariable("cid") Long cid){
		try {
			Category c = new Category();
			c.setCid(cid);
			return ResponseEntity.ok(this.quizService.getActiveQuizzesOfCategory(c));
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			
		}
	}
	

}
