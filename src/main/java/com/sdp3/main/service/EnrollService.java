package com.sdp3.main.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdp3.main.repository.EnrollRepo;

@Service
public class EnrollService {

	@Autowired
	private EnrollRepo repo;
	

}
