package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Date;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {
	
	@Autowired
	private MockMvc mock;
	
	@MockBean
	private UserService service;
	
	
//	@Test
	public void ユーザーリスト画面のユーザー件数のテスト() throws Exception {
		when(service.count()).thenReturn(10);
		
		mock.perform(get("/userList"))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("合計：10件")));
			
	}
	
	@Test
	public void a() throws Exception {
		User user = new User();
		user.setUserId("kokij@gamail.xom");
		user.setAge(40);
		user.setUserName("test");
		user.setBirthday(null);
		user.setPassword("test");
		user.setMarriage(true);
		user.setRole("test_role");
		
		boolean b = service.insert(user);

		assertTrue(b);
		
//		User us = service.selectOne("kokij@gamail.xom");
//		System.out.println(us);
//		mock.perform(get("/userDetail/kokij@gamail.xom"))
//		.andExpect(status().isOk())
//		.andExpect(content().string(containsString("合計：1件")));
		
	}
}
