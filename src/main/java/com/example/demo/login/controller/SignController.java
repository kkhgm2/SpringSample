package com.example.demo.login.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.GroupOrder;
import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;

@Controller
public class SignController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/signup")
	public String getSignup(@ModelAttribute SignupForm form, Model model) {
		Map<String, String> radioMarrige = new RadioButton().initRadioMarrige();
		model.addAttribute("radioMarrige", radioMarrige);
		
		return "/login/signup";
	}
	
	@PostMapping("/signup")
	public String postSignUp(@ModelAttribute @Validated(GroupOrder.class) SignupForm form,
			BindingResult bindResult, Model model) {
		if(bindResult.hasErrors()) {
			return getSignup(form, model);
		}
		System.out.println(form);
		
		User user = new User();
		user.dateMappingToUserFromForm(user, form);
		user.setRole("ROLE_GENERAL");
		
		boolean result = userService.insert(user);
		if (result) {
			System.out.println("インサート成功");
		} else {
			System.out.println("インサート失敗");
		}
		
		return "redirect:/login";
	}
	
	@ExceptionHandler(DataAccessException.class)
	public String dataAccessExeptionHandler(DataAccessException e, Model model) {
		model.addAttribute("error", "内部サーバーエラー： exceptionHandler");
		model.addAttribute("message", "signupController で dataAccessException が発生しました");
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
		
		return "error";
	}
	
	@ExceptionHandler(Exception.class)
	public String exeptionHandler(Exception e, Model model) {
		model.addAttribute("error", "内部サーバーエラー： exceptionHandler");
		model.addAttribute("message", "signupController で Exception が発生しました");
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
		
		return "error";
	}
}
