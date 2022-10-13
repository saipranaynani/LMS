package com.sdp3.main.controller;

import java.security.Principal;
import java.util.List;

import com.sdp3.main.entity.Request;
import com.sdp3.main.entity.User;
import com.sdp3.main.repository.RequestRepo;
import com.sdp3.main.service.EmailSenderService;
import com.sdp3.main.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
	private UserService service;

	@Autowired
	private EmailSenderService eservice;

	@Autowired
	private RequestRepo arepo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

    @ModelAttribute
	public void addCommonData(Model model, Principal principal) {
		String userName = principal.getName();
		User user = service.getEmail(userName);
		model.addAttribute("user",user);
	}

    @GetMapping("/dashboard")
	public String dashboard(Model model, Principal principal) {
		model.addAttribute("title","Dashboard");
		return "admin/admin_dashboard";
	}

    @GetMapping("/requests")
	public String Requests(Model model) {
		List<Request> applist = arepo.findAll();
		model.addAttribute("title","Requests");
		model.addAttribute("appList", applist);
		return "admin/admin_requests";
	}
	@GetMapping("/requests/delete/{id}")
	public String deleteApplication(@PathVariable("id") int id,RedirectAttributes ra){
		this.arepo.deleteById(id);
		return "redirect:/admin/requests";
	}
	
    @GetMapping("/instructors")
	public String addInstructor(Model model) {
		String role = "ROLE_TEACHER";
		List<User> u = service.findAllIntructors(role);
		model.addAttribute("ins", u);
		model.addAttribute("title","Instructors");
		return "admin/admin_instructors";
	}

	@GetMapping("/requests/accept/{id}")
	public String acceptApplication(@PathVariable("id") int id,RedirectAttributes ra){
		Request r = this.arepo.getById(id);
		User u = new User();
		u.setEmail(r.getEmail());
		u.setFullname(r.getFullName());
		u.setRole("ROLE_TEACHER");
		u.setPassword(passwordEncoder.encode("newUser30061"));

		//Email sedning
		String toEmail = r.getEmail();
		String subject = "Verification of Application - LMS";
		String body = "Dear "+r.getFullName()+",\n Your Application has been verified. Please use these Cendentials to access your account. \nUsername/Email : "+r.getEmail()+"\n Password : newUser30061\n Team LMS.\n Thank You.";
		eservice.sendSimpleEmail(toEmail, body, subject);
		this.service.saveUser(u);
		this.arepo.deleteById(id);
		return "redirect:/admin/instructors";
	}
}
