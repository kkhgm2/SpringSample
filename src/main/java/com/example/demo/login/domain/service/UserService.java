package com.example.demo.login.domain.service;


import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;

@Transactional
@Service
public class UserService {
	@Autowired
	@Qualifier("UserDaoImpl")
	UserDao dao;
	
	public boolean insert(User user) {
		System.out.println(user);
		int rowNumber = dao.insertOne(user);

		return rowNumber > 0;
	}
	
	public int count() {
		return dao.count();
	}
	
	public List<User> selectMany() {
		return dao.selectMany();
	}
	
	public User selectOne(String userId) {
		return dao.selectOne(userId);
	}
	
	public boolean updateOne(User user) {
		int rowNumber = dao.updateOne(user);
		
		return rowNumber > 0;
	}
	
	public boolean deleteOne(String userId) {
		int rowNumber = dao.deleteOne(userId);
		
		return rowNumber > 0;
	}
	
	public void userCsvOut() {
		dao.userCsvOut();
	}
	
	public byte[] getFile(String fileName) throws IOException {
		FileSystem fs = FileSystems.getDefault();
		Path path = fs.getPath(fileName);
		byte[] bytes = Files.readAllBytes(path);
		
		return bytes;
	}
}
