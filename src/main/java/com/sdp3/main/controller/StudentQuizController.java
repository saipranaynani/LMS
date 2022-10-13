package com.sdp3.main.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.sdp3.main.entity.Result;
import com.sdp3.main.entity.User;
import com.sdp3.main.entity.quiz.Question;
import com.sdp3.main.entity.quiz.QuestionForm;
import com.sdp3.main.entity.quiz.Quiz;
import com.sdp3.main.repository.CourseRepo;
import com.sdp3.main.repository.EnrollRepo;
import com.sdp3.main.service.CourseService;
import com.sdp3.main.service.QuestionService;
import com.sdp3.main.service.QuizService;
import com.sdp3.main.service.UserService;

@Controller
@RequestMapping("/student/view/course/{id}/tab/quizes")
public class StudentQuizController {
	@Autowired
	private UserService service;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseRepo crepo;
	
	@Autowired
	private EnrollRepo erepo;

	@Autowired
	private QuizService qservice;
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	Result result;
	
	@ModelAttribute
	public void addCommonData(Model model, Principal principal) {
		String userName = principal.getName();
		User user = service.getEmail(userName);
		model.addAttribute("user",user);
	}
	
	@ModelAttribute("result")
	public Result getResult() {
		return result;
	}
	
	@GetMapping("/{quizId}/startQuiz")
	public String StartQuiz(@PathVariable("id") Long courseid,@PathVariable("quizId") Long quizId,Model model) {
		Course course = crepo.findByCourseCode(courseid);
		Quiz quiz = qservice.getQuiz(quizId);
		String quizTitle = quiz.getTitle();
		model.addAttribute(course);
		model.addAttribute("title",course.getCourseName());
		model.addAttribute("cid",courseid);
		model.addAttribute("quiz",quiz);
		model.addAttribute("quizTitle",quizTitle);
		
		return "student/view_tab/instructionsPage";
	}
	
	@GetMapping("/{quizId}/attempt")
	public String AttemptQuiz(@PathVariable("id") Long courseid,@PathVariable("quizId") Long quizId,Model model) {
		Course course = crepo.findByCourseCode(courseid);
		Quiz quiz = qservice.getQuiz(quizId);
		String quizTitle = quiz.getTitle();
		model.addAttribute(course);
		model.addAttribute("title",course.getCourseName());
		model.addAttribute("cid",courseid);
		model.addAttribute("quiz",quiz);
		model.addAttribute("quizTitle",quizTitle);
		
		QuestionForm qForm = questionService.getQuestions(quiz.getQid());
		model.addAttribute("qForm",qForm);
		
		
		return "student/view_tab/attemptQuiz";
	}
	
	@PostMapping("/{quizId}/submitQuiz")
	public String submit(@PathVariable("id") Long courseid,@PathVariable("quizId") Long quizId,@ModelAttribute("qForm") QuestionForm qForm, Model model,Principal principal,
			RedirectAttributes redirectAttributes) {
		String userName = principal.getName();
		Quiz quiz1 = qservice.getQuiz(quizId);
		String quizTitle = quiz1.getTitle();
		result.setCourseId(courseid);
		result.setQuizId(quizId);
		result.setUsername(userName);
		result.setTotalCorrect(qservice.getResult(qForm));
		result.setAttemptedDate(new java.util.Date());
		result.setQuizTitle(quizTitle);
		result.setNumberOfQuestions(quiz1.getNumberOfQuestions());
		result.setMaxScore(quiz1.getMaxMarks());
		double score = (quiz1.getMaxMarks()/quiz1.getNumberOfQuestions()) * qservice.getResult(qForm);
		result.setScore(score);
		qservice.saveScore(result);
		
		Set<Quiz> quiz = this.qservice.getQuizesByCourseCode(courseid);
		redirectAttributes.addAttribute("quiz",quiz);
		redirectAttributes.addAttribute("title","This is Quizes Page");
		redirectAttributes.addAttribute("courseid",courseid);
		
		return "redirect:/student/view/course/{courseid}/tab/quizes";
	}
	
}
