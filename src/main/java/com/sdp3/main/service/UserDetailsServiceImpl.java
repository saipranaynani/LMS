package com.sdp3.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sdp3.main.config.CustomUserDetails;
import com.sdp3.main.entity.User;
import com.sdp3.main.repository.UserRepo;

public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepo.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("User Not Found !!");
		}
		
		CustomUserDetails cud = new CustomUserDetails(user);
		
		return cud;
	}

}
