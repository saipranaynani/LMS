package com.sdp3.main.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdp3.main.entity.quiz.Question;
import com.sdp3.main.entity.quiz.QuestionForm;
import com.sdp3.main.entity.quiz.Quiz;
import com.sdp3.main.repository.QuestionRepo;

@Service
public class QuestionServiceImpl implements QuestionService{

	@Autowired
	private QuestionRepo repo;
	
	@Autowired
	QuestionForm qForm;
	
	@Override
	public Question addQuestion(Question question) {
		// TODO Auto-generated method stub
		return this.repo.save(question);
	}

	@Override
	public Question updateQuestion(Question question) {
		// TODO Auto-generated method stub
		return this.repo.save(question);
	}

	@Override
	public Set<Question> getQuestions() {
		// TODO Auto-generated method stub
		return new HashSet<>(this.repo.findAll());
	}

	@Override
	public Question getQuestion(long questionId) {
		// TODO Auto-generated method stub
		return this.repo.findById(questionId).get();
	}


	@Override
	public Set<Question> getQuestionsByQuizid(Long qid) {
		// TODO Auto-generated method stub
		return this.repo.findAllByQuizid(qid);
	}

	@Override
	public QuestionForm getQuestions(long qid) {
		List<Question> allQues = repo.findAllByQuizId(qid);
		qForm.setQuestions(allQues);
		return qForm;
	}


}
