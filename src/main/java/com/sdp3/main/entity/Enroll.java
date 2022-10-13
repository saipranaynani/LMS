package com.sdp3.main.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Enroll {
	@Id
	@GeneratedValue
	private long eid;
	
	private int course_id;
	
    private Date joined;
	
	private String studentName;
	private String stduentEmail;
	private String role;

	private String courseName;
	private long courseCode;
	
	
    public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public long getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(long courseCode) {
		this.courseCode = courseCode;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStduentEmail() {
		return stduentEmail;
	}
	public void setStduentEmail(String stduentEmail) {
		this.stduentEmail = stduentEmail;
	}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public long getEid() {
		return eid;
	}
	public void setEid(long eid) {
		this.eid = eid;
	}
	
	public Date getJoined() {
		return joined;
	}
	public void setJoined(Date joined) {
		this.joined = joined;
	}
	
}
