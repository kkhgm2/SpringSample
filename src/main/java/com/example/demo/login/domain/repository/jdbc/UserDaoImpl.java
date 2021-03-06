package com.example.demo.login.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;

@Repository("UserDaoImpl")
public class UserDaoImpl implements UserDao {
	@Autowired
	JdbcTemplate jdbc;
	
	
	@Override
	public int count() throws DataAccessException {
		int count = jdbc.queryForObject("select count(*) from m_user", Integer.class);
		return count;
	}

	@Override
	public int insertOne(User user) throws DataAccessException {
		System.out.println(user);
		int rowNumber = jdbc.update("insert into m_user ("
				+ "user_id,"
				+ "password,"
				+ "user_name,"
				+ "birthday,"
				+ "age,"
				+ "marriage,"
				+ "role)"
				+ "values(?,?,?,?,?,?,?)"
				, user.getUserId()
				, user.getPassword()
				, user.getUserName()
				, user.getBirthday()
				, user.getAge()
				, user.isMarriage()
				, user.getRole());

		return rowNumber;
	}

	@Override
	public User selectOne(String userId) throws DataAccessException {
		Map<String, Object> map = jdbc.queryForMap("select * from m_user "
				+ "where user_id = ?", userId);
		User user = new User();
		user.dataMapping(user, map);
		
		return user;
	}

	@Override
	public List<User> selectMany() throws DataAccessException {
		List<Map<String, Object>> getList = jdbc.queryForList("select * from m_user");
		List<User> userList = new ArrayList<>();
		for(Map<String, Object> map : getList) {
			User user = new User();
			user.dataMapping(user, map);
			userList.add(user);
		}
		
		return userList;
	}

	@Override
	public int updateOne(User user) throws DataAccessException {
		int rowNumber = jdbc.update("update m_user "
				+ "set "
				+ "password = ?, "
				+ "user_name = ?,"
				+ "birthday = ?, "
				+ "age = ?,"
				+ "marriage = ?"
				+ "where user_id = ?"
				, user.getPassword()
				, user.getUserName()
				, user.getBirthday()
				, user.getAge()
				, user.isMarriage()
				, user.getUserId()
				);
		
		if(true) throw new DataAccessException("transactional test") {};	
		
		
		return rowNumber;
	}

	@Override
	public int deleteOne(String userId) throws DataAccessException {
		int rowNumber = jdbc.update("delete from m_user where user_id = ?", userId);
		
		return rowNumber;
	}

	@Override
	public void userCsvOut() throws DataAccessException {
		String sql = "select * from m_user";
		
		UserRowCallbackHandler handler = new UserRowCallbackHandler();
		jdbc.query(sql, handler);
	}
}
