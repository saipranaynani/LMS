package com.sdp3.main.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.sdp3.main.entity.Result;
import com.sdp3.main.entity.quiz.QuestionForm;
import com.sdp3.main.entity.quiz.Quiz;

@Service
public interface QuizService {
	
public Quiz addQuiz(Quiz quiz);
	
	public Quiz updateQuiz(Quiz quiz);
	
	public Set<Quiz> getQuizes();
	
	public Quiz getQuiz(Long quizId);
	
	public void deleteQuiz(Long quizId);

	public Set<Quiz> getQuizesByCourseCode(Long id);
	
	public int getResult(QuestionForm qForm);

	void saveScore(Result result);


}
