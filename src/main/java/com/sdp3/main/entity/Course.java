package com.sdp3.main.entity;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sdp3.main.entity.quiz.Question;
import com.sdp3.main.entity.quiz.Quiz;

@Entity
public class Course {
	@Id
	@GeneratedValue
	private int id;
	private long courseCode;
	private String courseName;
	private String courseInstructor;
	private String courseInstructorEmail;
	private String accessCode;
	private long numberOfParticipants;
	
	public long getNumberOfParticipants() {
		return numberOfParticipants;
	}
	public void setNumberOfParticipants(long numberOfParticipants) {
		this.numberOfParticipants = numberOfParticipants;
	}
	public String getAccessCode() {
		return accessCode;
	}
	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}
	public String getCourseInstructorEmail() {
		return courseInstructorEmail;
	}
	public void setCourseInstructorEmail(String courseInstructorEmail) {
		this.courseInstructorEmail = courseInstructorEmail;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(long courseCode) {
		this.courseCode = courseCode;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseInstructor() {
		return courseInstructor;
	}
	public void setCourseInstructor(String courseInstructor) {
		this.courseInstructor = courseInstructor;
	}
	
	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String toString() {
		return " "+this.getId()+" "+this.getCourseCode()+" "+this.getCourseName()+" ";
	}
}
