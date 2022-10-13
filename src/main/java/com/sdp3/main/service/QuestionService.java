package com.sdp3.main.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.sdp3.main.entity.quiz.Question;
import com.sdp3.main.entity.quiz.QuestionForm;
import com.sdp3.main.entity.quiz.Quiz;

@Service
public interface QuestionService{
	public Question addQuestion(Question question);
	
	public Question updateQuestion(Question question);
	
	public Set<Question> getQuestions();
	
	public Question getQuestion(long questionId);


	public Set<Question> getQuestionsByQuizid(Long qid);

	public QuestionForm getQuestions(long qid);
	
}
