package com.example.demo.login.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;

@Controller
public class HomeController {

	@Autowired
	UserService userService; 
	
	@GetMapping("/home")
	public String getHome(Model model) {
		model.addAttribute("contents", "login/home::home_contents");
		return "login/homeLayout";
	}
	
	@GetMapping("/userList")
	public String getUserList(Model model) {
		
		List<User> userList = userService.selectMany();
		int count = userService.count();
		
		model.addAttribute("contents", "login/userList::userList_contents");
		model.addAttribute("userList", userList);		
		model.addAttribute("userListCount", count);
		
		return "login/homeLayout";
	}
	
	@GetMapping("/userDetail/{id:.+}")
	public String getUserDetail(@ModelAttribute SignupForm form, Model model, 
			@PathVariable("id") String userId) {
		System.out.println("userId=" + userId);
		Map<String, String> radioMarrige = new RadioButton().initRadioMarrige();
		model.addAttribute("radioMarrige", radioMarrige);
		model.addAttribute("contents", "login/userDetail::userDetail_contents");
		
		if(userId != null && userId.length() > 0) {
			User user = userService.selectOne(userId);
			form.setUserId(user.getUserId());
			form.setUserName(user.getUserName());
			form.setBirthday(user.getBirthday());
			form.setAge(user.getAge());
			form.setMarriage(user.isMarriage());
			
			model.addAttribute("signupForm", form);
		}
		
		return "login/homeLayout";
	}
	
//	<button type="submit" name="update">更新</button>と対応
	@PostMapping(value="/userDetail", params="update")
	public String postUserDetaillUpdate(@ModelAttribute SignupForm form, Model model) {
		User user = new User();
		user.dateMappingToUserFromForm(user, form);
		
		try {
					
			boolean result = userService.updateOne(user);
			if(result) {
				model.addAttribute("result", "更新完了"); 
			} else {
				model.addAttribute("result", "更新失敗"); 
			}
		} catch (DataAccessException e) {
			model.addAttribute("result", "更新失敗　トランザクションテスト"); 
		}
		return getUserList(model);
	}
	
	@PostMapping(value="/userDetail", params="delete")
	public String postUserDetailDelete(@ModelAttribute SignupForm form, Model model) {
		
		
		boolean result = userService.deleteOne(form.getUserId());
		
		if(result) {
			model.addAttribute("result", "更新完了"); 
		} else {
			model.addAttribute("result", "更新失敗"); 
		}
		
		return getUserList(model);
		
	}
	
	@PostMapping("/logout")
	public String postLogout() {
		return "redirect:/login";
	}

	@GetMapping("/userList/csv")
	public ResponseEntity<byte[]> getUserListCsv(Model model) {
		userService.userCsvOut();
		byte[] bytes = null;
		try {
			bytes = userService.getFile("sample.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		HttpHeaders header = new HttpHeaders();
		header.add("Contet-Type", "text/csv;charaset=UTF-8");
		header.setContentDispositionFormData("filename", "sameple.csv");
		
		return new ResponseEntity<>(bytes, header, HttpStatus.OK);
	}
	
	
}
