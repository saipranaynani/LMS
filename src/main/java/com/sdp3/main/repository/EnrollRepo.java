package com.sdp3.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sdp3.main.entity.Enroll;

@Repository
public interface EnrollRepo extends JpaRepository<Enroll, Long>{
	
	@Query("SELECT e.course_id FROM Enroll AS e WHERE e.stduentEmail = :stduentEmail")
	List<Integer> findAllCourseId(@Param("stduentEmail") String email);

	@Query("FROM Enroll AS e WHERE e.course_id = :course_id")
	List<Enroll> findAllEnrolledByCourseId(@Param("course_id") int course_id);

	@Query("FROM Enroll AS e WHERE e.stduentEmail = :stduentEmail")
    List<Enroll> findAllEnrolls(@Param("stduentEmail") String email);
	
	
}
