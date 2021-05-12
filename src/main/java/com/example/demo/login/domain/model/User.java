package com.example.demo.login.domain.model;

import java.util.Date;
import java.util.Map;

import lombok.Data;

@Data
public class User {

	private String userId;
	private String password;
	private String userName;
	private Date birthday;
	private int age;
	private boolean marriage;
	private String role;
	
	
	public void dataMapping (User user, Map<String, Object> map) {
		user.setUserId((String) map.get("user_id"));
		user.setPassword((String) map.get("password"));
		user.setUserName((String) map.get("user_id"));
		user.setBirthday((Date) map.get("birthday"));
		user.setAge((int) map.get("age"));
		user.setMarriage((boolean) map.get("marriage"));
		user.setRole((String) map.get("role"));
	}
	
	public void dateMappingToUserFromForm (User user, SignupForm form) {
		user.setUserId(form.getUserId());
		user.setPassword(form.getPassword());
		user.setUserName(form.getUserName());
		user.setBirthday(form.getBirthday());
		user.setAge(form.getAge());
		user.setMarriage(form.isMarriage());
	}
}
