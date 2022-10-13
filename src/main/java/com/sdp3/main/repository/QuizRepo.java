package com.sdp3.main.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sdp3.main.entity.quiz.QuestionForm;
import com.sdp3.main.entity.quiz.Quiz;

public interface QuizRepo extends JpaRepository<Quiz, Long>{

	@Query("FROM Quiz as q WHERE q.courseCode =:id")
	Set<Quiz> getAllByCourseCode(Long id);

	

}
