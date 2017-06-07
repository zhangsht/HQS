package com.iQueue.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.iQueue.model.User;
import com.iQueue.service.DataSourceUtills;

public class UserDao implements UserDaoImp {
private JdbcTemplate jdbcTemplate;
	
	public UserDao() {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		DataSourceUtills dataSourceUtills = (DataSourceUtills) context.getBean("dataSourceUtils");
		jdbcTemplate = dataSourceUtills.getJbdcTemplate();
	}
	
	/*
	 * ͨ��RowMapper��ֵ��user
	 * RowMapper���Խ������е�ÿһ�з�װ���û��������
	 * �����ݿ��ѯ�У�������ص��������û��Զ������������Ҫ��װ
	 */
	private static final class UserMapper implements RowMapper<User> {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setOId(rs.getString("o_id"));
			user.setUserName(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setStatus(rs.getString("status"));
			return user;
		}
	}
	
	/*
	 * ������Ϣ
	 * @see UserImp#insert(User)
	 */
	public void insert(User user) {
		String sql = "insert into user (o_id, username, password, status) values(?, ?, ?, ?)";
		jdbcTemplate.update(sql, user.getOId(), user.getUserName(), user.getPassword(), user.getStatus());
	}
	
	
	/*
	 * ɾ����Ϣ
	 * @see UserImp#delete(String)
	 */
	public void delete(String id) {
		String sql = "delete from user where o_id=?";
		jdbcTemplate.update(sql, id);
	}
	
	/*
	 * ��ѯ��Ϣ
	 * @see UserImp#selectByID(String)
	 */
	public User selectByID(String id) {
		String sql = "select * from user where o_id=?";
		User user = jdbcTemplate.queryForObject(sql,
				BeanPropertyRowMapper.newInstance(User.class), id);
		return user;
	}
	
	public boolean isEmpty(String userName) {
		String SQL = "select * from user where username = ?";
		List items = jdbcTemplate.query(SQL, new Object[] { userName }, new UserMapper());
		return items.isEmpty();
	}
	
	/*
	 * ������Ϣ
	 * @see UserDaoImp#update(User)
	 */
	public void update(User user) {
		String sql = "update user set o_id=? where o_id=?";
		jdbcTemplate.update(sql,
				user.getOId());
	}
	
	public User getUser(String userName) {
		String SQL = "select * from user where username = ?";
		List<User> items = jdbcTemplate.query(SQL, new Object[] { userName }, new UserMapper());
		if (items.isEmpty()) {
			return null;
		}
		return items.get(0);
	}
	
	public User getUserWithPassword(String userName, String password) {
		String SQL = "select * from user where username = ? and password = ?";
		List<User> items = jdbcTemplate.query(SQL, new Object[] { userName, password }, new UserMapper());
		if (items.isEmpty()) {
			return null;
		}
		return items.get(0);
	}
}
