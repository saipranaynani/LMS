package com.sdp3.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sdp3.main.entity.Result;

@Repository
public interface ResultRepo extends JpaRepository<Result,Integer>{

	@Query("FROM Result as r where r.username=:username and r.courseId=:id")
	List<Result> findAllByUserNameAndCourse(@Param("username")String username,@Param("id") Long id);

	@Query("FROM Result as r where r.courseId=:id")
	List<Result> findAllByUserNameAndCourse(Long id);

}
