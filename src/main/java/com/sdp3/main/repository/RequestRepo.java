package com.sdp3.main.repository;

import com.sdp3.main.entity.Request;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RequestRepo extends JpaRepository<Request, Integer>{

    @Query("FROM Request as a WHERE a.email =:email")
    Request findApplicantByEmail(String email);
    
}
