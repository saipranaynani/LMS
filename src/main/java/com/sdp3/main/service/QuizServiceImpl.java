package com.sdp3.main.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdp3.main.entity.Result;
import com.sdp3.main.entity.quiz.Question;
import com.sdp3.main.entity.quiz.QuestionForm;
import com.sdp3.main.entity.quiz.Quiz;
import com.sdp3.main.repository.QuizRepo;
import com.sdp3.main.repository.ResultRepo;

@Service
public class QuizServiceImpl implements QuizService{

	@Autowired
	private QuizRepo repo;
	
	@Autowired
	private ResultRepo rRepo;
	
	@Override
	public Quiz addQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return this.repo.save(quiz);
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return this.repo.save(quiz);
	}

	@Override
	public Set<Quiz> getQuizes() {
		// TODO Auto-generated method stub
		return new HashSet<>(this.repo.findAll());
	}

	@Override
	public Quiz getQuiz(Long quizId) {
		// TODO Auto-generated method stub
		return this.repo.getOne(quizId);
	}

	@Override
	public void deleteQuiz(Long quizId) {
		// TODO Auto-generated method stub
		this.repo.deleteById(quizId);
	}

	@Override
	public Set<Quiz> getQuizesByCourseCode(Long id) {
		
		return repo.getAllByCourseCode(id);
	}
	
	@Override
	public int getResult(QuestionForm qForm) {
		int correct = 0;
		
		for(Question q: qForm.getQuestions())
			if(q.getAnswer().equals(q.getChoose()))
				correct++;
		
		return correct;
	}
	
	@Override
	public void saveScore(Result result) {
		Result saveResult = new Result();
		saveResult.setUsername(result.getUsername());
		saveResult.setTotalCorrect(result.getTotalCorrect());
		saveResult.setCourseId(result.getCourseId());
		saveResult.setQuizId(result.getQuizId());
		saveResult.setAttemptedDate(result.getAttemptedDate());
		saveResult.setMaxScore(result.getMaxScore());
		saveResult.setNumberOfQuestions(result.getNumberOfQuestions());
		saveResult.setQuizTitle(result.getQuizTitle());
		saveResult.setScore(result.getScore());
		rRepo.save(saveResult);
	}

}
