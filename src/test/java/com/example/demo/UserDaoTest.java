package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
//import org.junit.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;

//@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserDaoTest {

	@Autowired
	@Qualifier("UserDaoImpl")
	UserDao dao;
	
	@Test
	public void countTest() {
		assertEquals(dao.count(), 1);
	}
	
	@Test
	@Sql("/testDate.sql")
	public void contTest2() {
		assertEquals(dao.count(), 2);
	}
	
	@Test
	public void test() {
		User user = new User();
		user.setUserId("kokij@gamail.xom");
		user.setAge(40);
		user.setUserName("test");
		user.setBirthday(null);
		user.setPassword("test");
		user.setMarriage(true);
		user.setRole("test_role");
		
		dao.insertOne(user);
		assertEquals(dao.count(), 2);
	}
}
