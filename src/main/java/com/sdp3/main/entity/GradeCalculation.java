package com.sdp3.main.entity;

import org.springframework.stereotype.Component;

import com.sdp3.main.entity.quiz.Quiz;

@Component
public class GradeCalculation {
	private Quiz quiz;
	private long totalCorrect;
	public Quiz getQuiz() {
		return quiz;
	}
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	public long getTotalCorrect() {
		return totalCorrect;
	}
	public void setTotalCorrect(long totalCorrect) {
		this.totalCorrect = totalCorrect;
	}
	
	
}
