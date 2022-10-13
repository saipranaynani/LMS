package com.sdp3.main.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sdp3.main.entity.Course;
import com.sdp3.main.entity.Enroll;
import com.sdp3.main.entity.GradeCalculation;
import com.sdp3.main.entity.Result;
import com.sdp3.main.entity.User;
import com.sdp3.main.entity.quiz.Quiz;
import com.sdp3.main.repository.CourseRepo;
import com.sdp3.main.repository.EnrollRepo;
import com.sdp3.main.repository.ResultRepo;
import com.sdp3.main.repository.UserRepo;
import com.sdp3.main.service.CourseService;
import com.sdp3.main.service.EnrollService;
import com.sdp3.main.service.QuizService;
import com.sdp3.main.service.UserService;

@Controller
@RequestMapping("/student")
public class UserController {

	@Autowired
	private UserService service;
	
	@Autowired
	private UserRepo urepo;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseRepo repo;
	
	@Autowired
	private ResultRepo rrepo;
	
	@Autowired
	private EnrollRepo erepo;
	
	@Autowired
	private EnrollService eservice;

	@Autowired
	private QuizService qservice;
	
	
	@ModelAttribute
	public void addCommonData(Model model, Principal principal) {
		String userName = principal.getName();
		User user = service.getEmail(userName);
		model.addAttribute("user",user);
	}
	
	@RequestMapping("/dashboard")
	public String dashboard(Model model,Principal principal) {
		String userName = principal.getName();
		User user = this.urepo.findByEmail(userName);
		List<Integer> enroll = this.erepo.findAllCourseId(user.getEmail());
		List<Course> course = new ArrayList<Course>();
		
		for(int i=0;i<enroll.size();i++) {
			course.add(this.courseService.getCourseById(enroll.get(i)));
		}
		model.addAttribute("courseEnrolled",course);
		model.addAttribute("title","Dashboard");
		return "student/student_dashboard";
	}
	
	
	@GetMapping("/studentProfile")
	public String userProfile(Model model){
		model.addAttribute("title","This is Profile");
		return "student/student_profile";
	}
	
	@GetMapping("/courses")
	public String courses(Model model,Principal principal) {
		String userName = principal.getName();
		User user = this.urepo.findByEmail(userName);
		List<Enroll> enroll = this.erepo.findAllEnrolls(user.getEmail());

		model.addAttribute("course",enroll);
		
		model.addAttribute("title","Courses");
		return "student/student_courses";
	}
	
	@GetMapping("/view/course/{id}")
	public String viewCourse(@PathVariable("id") Long id,Model model) {
		Course course = repo.findByCourseCode(id);
		model.addAttribute(course);
		model.addAttribute("title",course.getCourseName());
		return "student/view_tab/about";
	}

	@GetMapping("/courses/enrollCourse/{courseId}")
	public String enroll(@PathVariable("courseId") Long cid,Model model) {
		Course course = repo.findByCourseCode(cid);
		model.addAttribute(course);
		model.addAttribute("title","Course Enrollment");
		model.addAttribute("course",course);
		
		return "student/enrollCourse";
	}


	@GetMapping("/view/course/{id}/tab/{tabid}")
    public String tab(@PathVariable("id") Long id, 
    		  @PathVariable("tabid") String tab,Model model,Principal principal) {
		String username = principal.getName();
		Course course = repo.findByCourseCode(id);
		model.addAttribute(course);
		model.addAttribute("title",course.getCourseName());
		
		
        if (tab.equals("about")){
            return  "student/view_tab/about";
        }
        
        
        else  if (tab.equals("material")){
            return  "student/view_tab/material";
        }
        
        
        else  if (tab.equals("quizes")){
        	Set<Quiz> quiz = this.qservice.getQuizesByCourseCode(id);
    		model.addAttribute("quiz",quiz);
            return  "student/view_tab/quizes";
        }
        
        
        
        else  if (tab.equals("grades")){
        	List<Result> result = this.rrepo.findAllByUserNameAndCourse(username,id);
        	
        	model.addAttribute("result",result);
            return  "student/view_tab/grades";
        }
        
        
        else  if (tab.equals("participants")){
        	Course course1 = this.courseService.getAllCourseById(id);
        	List<Enroll> usersEnrolledForParticularCourse = erepo.findAllEnrolledByCourseId(course1.getId());
        	
        	model.addAttribute("participantslist",usersEnrolledForParticularCourse);
            return  "student/view_tab/participants";
        }
        
        
        else{
            return  "student/view_tab/assignments";
        }
	}
	
}
