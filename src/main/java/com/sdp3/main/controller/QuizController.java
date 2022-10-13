package com.sdp3.main.controller;

import java.security.Principal;
import java.util.Set;

import javax.servlet.http.HttpSession;

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
import com.sdp3.main.entity.User;
import com.sdp3.main.entity.quiz.Question;
import com.sdp3.main.entity.quiz.Quiz;
import com.sdp3.main.repository.CourseRepo;
import com.sdp3.main.service.QuestionService;
import com.sdp3.main.service.QuizService;
import com.sdp3.main.service.UserService;

@Controller
@RequestMapping("/teacher/view/course/{id}/tab/quizes")
public class QuizController {

	@Autowired
	private UserService uservice;
	
	@Autowired
	private CourseRepo crepo;
	
	@Autowired
	private QuizService qservice;
	
	@Autowired
	private QuestionService questionService;
	
	@ModelAttribute
	public void addCommonData(Model model, Principal principal) {
		String userName = principal.getName();
		User user = uservice.getEmail(userName);
		model.addAttribute("user",user);
	}
	
	@RequestMapping("/createQuiz")
	public String createQuiz(@PathVariable("id") Long id,Model model) {
		Course course = crepo.findByCourseCode(id);
		model.addAttribute(course);
		model.addAttribute("title",course.getCourseName());
		model.addAttribute("id",id);
		return "teacher/view_tab/createQuiz";
	}
	
	@PostMapping("/addQuiz")
	public String addQuiz(@PathVariable("id") Long id,Model model,@ModelAttribute("Quiz") Quiz quiz,HttpSession session
			,RedirectAttributes ra) {
		Course course = crepo.findByCourseCode(id);
		ra.addAttribute(course);
		ra.addAttribute("title",course.getCourseName());
		ra.addAttribute("id",id);
		//Adding of quiz data
		Set<Quiz> quiz1 = this.qservice.getQuizesByCourseCode(id);
		ra.addAttribute("quiz",quiz1);
		this.qservice.addQuiz(quiz);		
		return "redirect:/teacher/view/course/{id}/tab/quizes";
	}
	
	@GetMapping("/{qid}/updateQuiz")
	public String updateQuiz(@PathVariable("id") Long id,Model model,@PathVariable("qid") Long qid) {
		Course course = crepo.findByCourseCode(id);
		Quiz quiz = this.qservice.getQuiz(qid);
		model.addAttribute("quiz",quiz);
		model.addAttribute("course",course);
		model.addAttribute("title",course.getCourseName());
		model.addAttribute("id",id);
		
		return "teacher/view_tab/updateQuiz";
	}
	@PostMapping("/{qid}/updateSaveQuiz")
	public String updateSaveQuiz(@PathVariable("id") Long id,Model model,@PathVariable("qid")Long qid,RedirectAttributes ra,@ModelAttribute("Quiz") Quiz quiz){
		this.qservice.updateQuiz(quiz);
		ra.addAttribute("id",id);
		return "redirect:/teacher/view/course/{id}/tab/quizes";
	}
	@GetMapping("/{qid}/allQuestions")
	public String allQuestions(@PathVariable("id") Long cid,@PathVariable("qid") Long qid,Model model) {
		Course course = crepo.findByCourseCode(cid);
		
		Quiz quiz = qservice.getQuiz(qid);
		String quizTitle = quiz.getTitle();
		Set<Question> question = questionService.getQuestionsByQuizid(qid);
		model.addAttribute(course);
		model.addAttribute("title",course.getCourseName());
		model.addAttribute("cid",cid);
		model.addAttribute("question",question);
		model.addAttribute("quizId",qid);
		model.addAttribute("quizTitle",quizTitle);
		return "/teacher/view_tab/allQuestions";
	}
	
	@GetMapping("/{qid}/addQuestion")
	public String addQuestions(@PathVariable("id") Long cid,@PathVariable("qid") Long qid,Model model) {
		Course course = crepo.findByCourseCode(cid);
		
		Quiz quiz = qservice.getQuiz(qid);
		String quizTitle = quiz.getTitle();
		Set<Question> question = questionService.getQuestionsByQuizid(qid);
		model.addAttribute(course);
		model.addAttribute("title",course.getCourseName());
		model.addAttribute("cid",cid);
		model.addAttribute("question",question);
		model.addAttribute("quizId",qid);
		model.addAttribute("quizTitle",quizTitle);
		return "/teacher/view_tab/addQuestions";
	}
	
	@PostMapping("/{qid}/saveQuestion")
	public String AddQuestions(@PathVariable("id") Long cid,@PathVariable("qid") Long qid,@ModelAttribute("Question") Question question
			,RedirectAttributes redirectAttributes) {
		Course course = crepo.findByCourseCode(cid);
		Quiz quiz = qservice.getQuiz(qid);
		String quizTitle = quiz.getTitle();
		Set<Question> question1 = questionService.getQuestionsByQuizid(qid);
		redirectAttributes.addAttribute("course",course);
		redirectAttributes.addAttribute("title",course.getCourseName());
		redirectAttributes.addAttribute("cid",cid);
		redirectAttributes.addAttribute("question",question1);
		redirectAttributes.addAttribute("quizId",qid);
		redirectAttributes.addAttribute("quizTitle",quizTitle);
		
		this.questionService.addQuestion(question);
		
		return "redirect:/teacher/view/course/{cid}/tab/quizes/{qid}/allQuestions";
         
	}
	
	// STDENT CONTOLLER FOR QUIZES
	
	
}
