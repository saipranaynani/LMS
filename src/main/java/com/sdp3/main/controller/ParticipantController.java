package com.sdp3.main.controller;

import java.security.Principal;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sdp3.main.entity.Course;
import com.sdp3.main.entity.Enroll;
import com.sdp3.main.entity.User;
import com.sdp3.main.repository.CourseRepo;
import com.sdp3.main.repository.EnrollRepo;
import com.sdp3.main.repository.UserRepo;
import com.sdp3.main.service.CourseService;
import com.sdp3.main.service.EmailSenderService;
import com.sdp3.main.service.UserService;

@Controller
@RequestMapping("/teacher/view/course/{cid}/tab/participants")

public class ParticipantController{

    @Autowired
	private UserService service;

	@Autowired
	private CourseRepo crepo;

	@Autowired
	private EnrollRepo erepo;

	@Autowired
	private UserRepo urepo;
	
	@Autowired
	private CourseService courseService;

	@Autowired
	private EmailSenderService emailserv;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@ModelAttribute
	public void addCommonData(Model model, Principal principal) {
		String userName = principal.getName();
		User user = service.getEmail(userName);
		model.addAttribute("user",user);
	}

    @PostMapping("/{id}/participantSave/user/{userId}")
	public String ParticipantSave(@PathVariable("cid") Long cid,@PathVariable("id") int id,@ModelAttribute("User") User user,Model model,HttpSession session,
	@ModelAttribute("Enroll") Enroll enroll,RedirectAttributes ra) {
		
		User userExists = service.getEmail(user.getEmail());
		String role = user.getRole();
		ra.addAttribute("cid",cid);
		if (userExists != null) {
			
			if(role.equals("student")) {
				enroll.setRole("STUDENT");
			}else {
				enroll.setRole("INSTRUCTUR");
			}
			Course c1 = this.crepo.getById(id);
			
			String toEmail = user.getEmail();
			String body = "Dear "+user.getFullname()+", \nYou have been added to a new course named "+c1.getCourseName()+".\n Thank You.\n Team LMS.";
			String subject="Added to a new Course - Learning Mangagement System";
			emailserv.sendSimpleEmail(toEmail, body, subject);

				enroll.setCourse_id(id);
			
				enroll.setJoined(new java.util.Date());
				enroll.setStudentName(user.getFullname());
				enroll.setStduentEmail(user.getEmail());
				enroll.setCourseCode(c1.getCourseCode());
				enroll.setCourseName(c1.getCourseName());
				c1.setNumberOfParticipants(c1.getNumberOfParticipants()+1);
				this.courseService.saveCourse(c1);
				this.erepo.save(enroll);

			return "redirect:/teacher/view/course/{cid}/tab/participants";
		}else {
		
			try {

					//Enroll user to course

					Course c1 = this.crepo.getById(id);
					
					enroll.setCourse_id(id);
					enroll.setJoined(new java.util.Date());
					enroll.setStudentName(user.getFullname());
					enroll.setStduentEmail(user.getEmail());
					enroll.setCourseCode(c1.getCourseCode());
					enroll.setCourseName(c1.getCourseName());
					c1.setNumberOfParticipants(c1.getNumberOfParticipants()+1);
				
				if(role.equals("student")) {
					user.setRole("ROLE_USER");
					enroll.setRole("STUDENT");
				}else {
					user.setRole("ROLE_TEACHER");
					enroll.setRole("INSTRUCTUR");
				}

				String toEmail = user.getEmail();
				String body = "Dear "+user.getFullname()+",\n You have been added to a new course named "+c1.getCourseName()+". \nPlease use these cerdentials to access the course "+
				"Email : "+user.getEmail()+"\n Password : "+user.getPassword()+".\n Thank You.\n Team LMS.";
				String subject="Added to a new Course - Learning Mangagement System";
				System.out.println("Email Service Triggered");
				emailserv.sendSimpleEmail(toEmail, body, subject);


				user.setPassword(passwordEncoder.encode(user.getPassword()));
				this.service.saveUser(user);
				this.courseService.saveCourse(c1);
				this.erepo.save(enroll);

				return "redirect:/teacher/view/course/{cid}/tab/participants";
			}catch(Exception e) {
				ra.addAttribute("user",new User());
				return "redirect:/teacher/view/course/{cid}/tab/participants";
			}
		}
		
	}

    @GetMapping("/addingParticipant")
    public String AddParticipant(@PathVariable("cid") Long cid,Model model){
		Course course = crepo.findByCourseCode(cid);
		model.addAttribute(course);
		model.addAttribute("title",course.getCourseName());
		model.addAttribute("id",cid);
        model.addAttribute("tilte","Adding Participant");
        return "teacher/view_tab/addParticipant";
    }

}