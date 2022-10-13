package com.sdp3.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.sdp3.main.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{


	User findByEmail(String email);

	User findByPassword(String password);

	@Query("from User as u where u.role =:role")
    public List<User> findAllIns(@Param("role") String role);

	
	
}
