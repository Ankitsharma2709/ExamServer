package com.exam.Service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.exam.model.quizModel.Question;

public interface QuestionService {
	public Question addQuestion(Question question);
	public Question updateQuestion(Question question);
	public Set<Question> getQuestions();
	public Question getQuestionById(Long qid);
	public Page<Question> getQuestionsOfQuiz(Long qid, PageRequest pageRequest);
	//this below method  serves the purpose of fetching questions based on quiz.
	//public Set<Question> getQuestionsOfQuiz(Quiz quiz);
	
	public List<Question> getSearchedQuestion(Long qid, String searchTerm);
	public void deleteQuestion(Long quesId);

}
