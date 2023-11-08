package org.jsp.quiz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller    //to load the class 
public class CommonController {
  
	@GetMapping("/")
	public String loadHome()
	{
		return "index";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session,ModelMap map)
	{
		session.invalidate();
		map.put("pass", "Loged Out Success");
		return "index";
	}
}
