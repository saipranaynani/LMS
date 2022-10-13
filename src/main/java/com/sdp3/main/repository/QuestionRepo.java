package com.sdp3.main.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sdp3.main.entity.quiz.Question;
import com.sdp3.main.entity.quiz.QuestionForm;
import com.sdp3.main.entity.quiz.Quiz;

public interface QuestionRepo  extends JpaRepository<Question, Long>{

	@Query("FROM Question as q WHERE q.quizId =:qid")
	Set<Question> findAllByQuizid(Long qid);

	@Query("FROM Question as q WHERE q.quizId =:qid")
	List<Question> findAllByQuizId(long qid);


}
