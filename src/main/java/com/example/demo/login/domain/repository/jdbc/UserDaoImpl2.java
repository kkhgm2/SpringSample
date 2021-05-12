package com.example.demo.login.domain.repository.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.User;

@Repository("UserDaoImpl2")
public class UserDaoImpl2 extends UserDaoImpl{

	@Autowired
	JdbcTemplate jdbc;
	
	@Override
	public User selectOne(String userId) throws DataAccessException {
		String sql = "select * from m_user where user_id = ?";
		UserRowMapper mapper = new UserRowMapper();
		
		return jdbc.queryForObject(sql, mapper, userId);
	}
	
	@Override
	public List<User> selectMany() throws DataAccessException {
		String sql = "select * from m_user";
		UserRowMapper mapper = new UserRowMapper();
		
		return jdbc.query(sql, mapper);
	}
}
