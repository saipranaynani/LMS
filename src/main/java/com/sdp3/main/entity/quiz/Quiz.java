package com.sdp3.main.entity.quiz;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sdp3.main.entity.Course;
import com.sdp3.main.entity.Result;

@Entity
public class Quiz {
	
	@Id
	@GeneratedValue
	private long qid;
	private String title;
	private long numberOfQuestions;
	private double maxMarks;
	private boolean active=false;
	private Long courseCode; 
	
	@ManyToOne
	private Result result;
	
	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public Long getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(Long courseCode) {
		this.courseCode = courseCode;
	}

	public Quiz() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public long getQid() {
		return qid;
	}
	public void setQid(long qid) {
		this.qid = qid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getNumberOfQuestions() {
		return numberOfQuestions;
	}
	public void setNumberOfQuestions(long numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}
	public double getMaxMarks() {
		return maxMarks;
	}
	public void setMaxMarks(double maxMarks) {
		this.maxMarks = maxMarks;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

	public Quiz(long qid, String title, long numberOfQuestions, double maxMarks, boolean active, Long courseCode,
			Result result) {
		super();
		this.qid = qid;
		this.title = title;
		this.numberOfQuestions = numberOfQuestions;
		this.maxMarks = maxMarks;
		this.active = active;
		this.courseCode = courseCode;
		this.result = result;
	}
	
	
}
