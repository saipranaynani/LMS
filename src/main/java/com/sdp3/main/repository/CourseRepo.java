package com.sdp3.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sdp3.main.entity.Course;

public interface CourseRepo extends JpaRepository<Course, Integer>{

	@Query("FROM Course as c WHERE c.courseInstructorEmail =:username")
	public  List<Course> getCourses(@Param("username")String username);

	@Query("FROM Course as c WHERE c.courseCode =:id")
	public Course findByCourseCode(@Param("id") Long id);

	@Query("SELECT c.accessCode FROM Course AS c WHERE c.courseCode = :cid")
	public String findAccessCodeByCourseCode(Long cid);

	@Query("SELECT c.accessCode FROM Course AS c WHERE c.id = :cid")
	public String findAccessCodeById(@Param("cid")int cid);

	Course findById(int long1);

}
