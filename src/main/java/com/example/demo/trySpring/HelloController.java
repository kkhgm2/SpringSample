package com.example.demo.trySpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
	
	@Autowired
	private HelloService service;
	
	@GetMapping("/hello")
	public String getHello() {
		return "hello";
	}

	@GetMapping("/hello/db")
	public String getHello(@RequestParam(value="text2", required=false) String str, Model model) {
		Employee emp = service.findOne(Integer.parseInt(str));
		
		model.addAttribute("id", emp.getEmployeeId());
		model.addAttribute("name", emp.getEmployeeName());
		model.addAttribute("age", emp.getAge());
		
		return "helloResponceDB";
	}

	
	@PostMapping("/hello")
	public String postRequest(@RequestParam("text1") String str, Model model) {
		model.addAttribute("sample", str);
		return "helloResponse";
	}
}
