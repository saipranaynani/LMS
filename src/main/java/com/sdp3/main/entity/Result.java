package com.sdp3.main.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Result {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private long quizId;
	private long courseId;
	private long totalCorrect = 0;
	private Date attemptedDate;
	private String quizTitle;
	private long numberOfQuestions;
	private double score;
	private double maxScore;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public long getQuizId() {
		return quizId;
	}
	public void setQuizId(long quizId) {
		this.quizId = quizId;
	}
	public long getCourseId() {
		return courseId;
	}
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	public long getTotalCorrect() {
		return totalCorrect;
	}
	public void setTotalCorrect(long totalCorrect) {
		this.totalCorrect = totalCorrect;
	}
	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Date getAttemptedDate() {
		return attemptedDate;
	}
	public void setAttemptedDate(Date attemptedDate) {
		this.attemptedDate = attemptedDate;
	}
	public String getQuizTitle() {
		return quizTitle;
	}
	public void setQuizTitle(String quizTitle) {
		this.quizTitle = quizTitle;
	}
	
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public long getNumberOfQuestions() {
		return numberOfQuestions;
	}
	public void setNumberOfQuestions(long numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}
	public double getMaxScore() {
		return maxScore;
	}
	public void setMaxScore(double maxScore) {
		this.maxScore = maxScore;
	}
	public Result(int id, String username, long quizId, long courseId, long totalCorrect, Date attemptedDate,
			String quizTitle, long numberOfQuestions, double score, double maxScore) {
		super();
		this.id = id;
		this.username = username;
		this.quizId = quizId;
		this.courseId = courseId;
		this.totalCorrect = totalCorrect;
		this.attemptedDate = attemptedDate;
		this.quizTitle = quizTitle;
		this.numberOfQuestions = numberOfQuestions;
		this.score = score;
		this.maxScore = maxScore;
	}
	
}
