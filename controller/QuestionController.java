package com.exam.controller;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.exam.Service.QuestionService;
import com.exam.Service.QuizService;
import com.exam.model.quizModel.Question;
import com.exam.model.quizModel.Quiz;

import javassist.NotFoundException;


@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {
	@Autowired
	private QuestionService questionService;
	@Autowired
	private QuizService quizService;
	
	//add question handler
	@PostMapping("/")
	public ResponseEntity<?> addQuestions(@RequestBody Question question){
		try {
			Question question1 = this.questionService.addQuestion(question);
			return ResponseEntity.ok(question1);
			
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	 
//	//get quesions handler
//	@GetMapping("/")
//	public ResponseEntity<?> getQuestions(){
//		return ResponseEntity.ok(this.questionService.getQuestions());
//		
//	}
	//get the questios of quiz
	@GetMapping("/quiz/{qId}")
	public ResponseEntity<?> getQuestionofQuizes(@PathVariable("qId") Long qid, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5")int size) throws NotFoundException{
		
//			/*
//			 * Quiz quiz = new Quiz(); quiz.setQid(qid); Set<Question> questionsOfQuiz =
//			 * this.questionService.getQuestionsOfQuiz(quiz); return
//			 * ResponseEntity.ok(questionsOfQuiz);
//			 */
		
		//in below commented we use the quizService to method to get the quiz data thn 
		//using that we getting the questions since it is mapped with it.
//			//quiz ;ia pehle
//			Quiz quiz = this.quizService.getQuizById(qid);
//			//saare questions nikaal lie ab saare to bhejne ni
//			
//			
//			Set<Question> questions = quiz.getQuestions();
//			//so size check krenge list bnake
//			List<Question> list = new ArrayList<>(questions);
//			
//			//zyada hai to list mein ublist replace krdenge 
//			if(list.size() > Integer.parseInt(quiz.getNumberOfQuestions())) {
//				//parseInt fistinclude krta and last paamater -1 tk chlta
//				list = list.subList(0,Integer.parseInt(quiz.getNumberOfQuestions()+1));
//			}
//			//shuffle krdie questions
//			Collections.shuffle(list);
//			
//			Pageable pageable = PageRequest.of(page, size);
//			Page<Question> paginatedQuestions = new PageImpl<>(list, pageable, list.size());
//			
//			
//			return ResponseEntity.ok(paginatedQuestions);
			
		
		
	        // Fetch paginated questions from your data source
	        Page<Question> paginatedQuestions = questionService.getQuestionsOfQuiz(qid, PageRequest.of(page, size));

	        return ResponseEntity.ok(paginatedQuestions);
	    
			
		
		
	}
	   
	@GetMapping("/quiz/search/{qid}/")
	public ResponseEntity<?> searchQuestionsByContent(@PathVariable("qid") Long qid , 
			@RequestParam String searchTerm) throws NotFoundException{
		Quiz quiz = quizService.getQuizById(qid);
		List<Question> searchResults = questionService.getSearchedQuestion(qid, searchTerm);
		return ResponseEntity.ok(searchResults);
	}
	
	
	//get single question
	@GetMapping("/{quesId}")
	public Question getSingleQuestion(@PathVariable("quesId") Long quesId) throws NotFoundException{
		return this.questionService.getQuestionById(quesId);
	}
	
	//update the questions
	@PutMapping("/")
	public Question updateQuestions(@RequestBody Question question) throws Exception {
		
		return this.questionService.updateQuestion(question);
		
	} 
	
	
	//delete handler
	@DeleteMapping("/{quesId}")
	public void deleteQuestions(@PathVariable("quesId") Long quesId) throws NotFoundException{
		this.questionService.deleteQuestion(quesId);
		
	}

}
