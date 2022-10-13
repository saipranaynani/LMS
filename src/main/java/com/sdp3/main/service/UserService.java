package com.sdp3.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdp3.main.entity.User;
import com.sdp3.main.repository.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo userRepo;
	
	public User saveUser(User user) {
		return userRepo.save(user);
	}
	
	public List<User> getUsers(){
		return userRepo.findAll();
	}
	
	public User getUser(int id){
		return userRepo.findById(id).orElse(null);
	}
	
	
	public String deleteUser(int id) {
		userRepo.deleteById(id);
		return "Delete Success";
	}
	
	public User updateUser(User user) {
		User existingUser = userRepo.findById(user.getId()).orElse(null);
		existingUser.setFullname(user.getFullname());
		existingUser.setEmail(user.getEmail());
		existingUser.setPassword(user.getPassword());
		
		return userRepo.save(existingUser);
	}

	public User getEmail(String email) {
		
		return userRepo.findByEmail(email);
	}

	public User getPassword(String password) {
		
		return userRepo.findByPassword(password);
	}

    public List<User> findAllIntructors(String role) {
        return userRepo.findAllIns(role);
    }

}
