package com.sdp3.main.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.sdp3.main.entity.Course;
import com.sdp3.main.repository.CourseRepo;


@Service
public class CourseService {
	
	@Autowired
	private CourseRepo repo;
	
	public Course saveCourse(Course course) {
		return repo.save(course);
	}

	public String getAccessCodeByCourseCode(Long cid) {
		// TODO Auto-generated method stub
		return repo.findAccessCodeByCourseCode(cid);
	}
	
	public Course getAllCourseById(long courseId){
		return this.repo.findByCourseCode(courseId);
	}

	public Course getCourseById(int long1) {
		// TODO Auto-generated method stub
		return this.repo.findById(long1);
	}

}
