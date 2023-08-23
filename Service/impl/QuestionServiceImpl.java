package com.exam.Service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.exam.Repo.QuestionRepository;
import com.exam.Service.QuestionService;
import com.exam.model.quizModel.Question;
@Service
public class QuestionServiceImpl implements QuestionService{
	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public Question addQuestion(Question question) {
		Question ques = this.questionRepository.save(question);
		return ques;
	}

	@Override
	public Question updateQuestion(Question question) {
		
		Question ques1 = this.questionRepository.save(question);
		return ques1;
	}

	@Override
	public Set<Question> getQuestions() {
		return new HashSet<>(this.questionRepository.findAll()); 
		/*
		 * Set<Question> quest = (Set<Question>)this.questionRepository.findAll();
		 * 
		 * 
		 * 
		 * return quest;
		 */
	}

	@Override
	public Question getQuestionById(Long quesId) {
		Optional<Question> questi = this.questionRepository.findById(quesId);
		Question less = questi.get();
		
		return less;
	}

	@Override
	public void deleteQuestion(Long quesId) {
		this.questionRepository.deleteById(quesId);
		
	}

	@Override
	public Page<Question> getQuestionsOfQuiz(Long qid, PageRequest pageRequest) {
		return this.questionRepository.findQuestionByQuiz(qid, pageRequest);
	}

	@Override
	public List<Question> getSearchedQuestion(Long qid, String searchTerm) {
		// TODO Auto-generated method stub
		return this.questionRepository.searchQuestionByContent(qid, searchTerm);
	}

	
	

}
