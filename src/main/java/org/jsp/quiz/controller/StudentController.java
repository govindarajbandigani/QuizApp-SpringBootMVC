package org.jsp.quiz.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.jsp.quiz.dto.Student;
import org.jsp.quiz.helper.LoginHelper;
import org.jsp.quiz.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	Student student;
	
	@Autowired
	StudentService service;
	
	
	@GetMapping("/login")
	public String loadLogin()
	{
		return "studentlogin";
	}
	
    @GetMapping("/signup")
    public String loadSignup(ModelMap map)
   {
      map.put("student", student);  //this is for validation purpose 
	  return "studentsignup";
   }
  
  @PostMapping("/signup")    //binding result for validation
  public String signup(@Valid Student student,BindingResult result,@RequestParam MultipartFile pic, ModelMap map) throws MessagingException, IOException
  {
	  if(result.hasErrors()) {
		  return "studentsignup";
	  }else {
		  return service.signup(student,pic,map);
	  }
  }
  
  @PostMapping("/verify-otp")
  public String verifyOtp(@RequestParam int id,@RequestParam int otp,ModelMap map)
  {
	  return service.verifyOtp(id,otp,map);
  }
  
  @GetMapping("/resend-otp/{id}")
  public String resendOtp(@PathVariable int id,ModelMap map) throws UnsupportedEncodingException, MessagingException
  {
	  return service.resendOtp(id,map);
  }
  
  @PostMapping("/login")
	public String login(LoginHelper helper,ModelMap map,HttpSession session)
	{
		return service.login(helper,map,session);
	}
  
  @GetMapping("/forgot-password")
	public String forgotPassword() {
		return "StudentForgotPassword";
	}

	@PostMapping("/forgot-password")
	public String forgotPassword(@RequestParam String email, ModelMap map)
			throws UnsupportedEncodingException, MessagingException {
		return service.forgotPassword(map, email);
	}

	@PostMapping("/reset-password")
	public String resetPassword(@RequestParam int id, @RequestParam int otp, @RequestParam String password,
			ModelMap map) {
		return service.resetPassword(id, otp, password, map);
	}
	
	@GetMapping("/add-batchcode")
	public String addBatchCode(HttpSession session, ModelMap map) {
		Student student = (Student) session.getAttribute("student");
		if (student == null) {
			map.put("fail", "Invalid Session");
			return "index";
		} else {
			return service.addBatchCode(map);
		}
	}

	@PostMapping("/add-batch")
	public String addBatchCode(HttpSession session, ModelMap map, Student student) {
		Student student1 = (Student) session.getAttribute("student");
		if (student1 == null) {
			map.put("fail", "Invalid Session");
			return "index";
		} else {
			return service.addBatchCode(student1, student, session, map);
		}
	}

	@GetMapping("/show-test")
	public String showTest(HttpSession session, ModelMap map) {
		Student student = (Student) session.getAttribute("student");
		if (student == null) {
			map.put("fail", "Invalid Session");
			return "index";
		} else {
			return service.showTest(student, session, map);
		}
	}

}
