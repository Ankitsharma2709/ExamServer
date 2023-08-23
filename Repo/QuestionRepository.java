package com.exam.Repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.exam.model.quizModel.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
	//finding questions associated with the quiz whihc we get through the controller where we pass the qid of 
	//quiz and then we get the related quiz of the qid and then we can get the associated questions.
    
//    public Set<Question> findByQuiz(Quiz quiz);
	
	//below we are getting the questions bades on quiz using pagination
	//pagination method
    
    @Query("from Question as q where q.quiz.qid = :qid")
    public Page<Question> findQuestionByQuiz(@Param("qid") Long qid, Pageable pageable);
    
    @Query("FROM Question q WHERE q.quiz.qid = :qid AND q.questionContent LIKE %:searchTerm%")
    List<Question> searchQuestionByContent(@Param("qid") Long qid, @Param("searchTerm") String searchTerm);
    
    
}
