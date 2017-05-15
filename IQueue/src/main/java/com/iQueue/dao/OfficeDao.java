package com.iQueue.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.iQueue.model.Office;

public class OfficeDao implements OfficeDaoImp {
	
	private JdbcTemplate jdbcTemplate;
	
	/*
	 * 通过RowMapper赋值给office
	 * RowMapper可以将数据中的每一行封装成用户定义的类
	 * 在数据库查询中，如果返回的类型是用户自定义的类型则需要包装
	 */
	private static final class OfficeMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			// 缺少医生信息和队列信息
			Office office = new Office();
			office.setOId(rs.getString("id"));
			office.setName(rs.getString("name"));
			return office;
		}
	}
	
	/*
	 * 查询所有的诊室数据信息
	 * @see OfficeDaoImp#find()
	 */
	public List find() {
		String sql = "select * from office";
		return jdbcTemplate.query(sql, new OfficeMapper());
	}
	
	/*
	 * 删除信息
	 * @see OfficeDaoImp#delete(String)
	 */
	public void delete(String id) {
		String sql = "delete from office where id=?";
		jdbcTemplate.update(sql, id);
	}
	
	/*
	 * 插入信息
	 * @see OfficeDaoImp#insert(Office)
	 */
	public void insert(Office office) {
		// 还缺少医生信息和队列信息
		String sql = "insert into office (id,name) values(?,?)";
		jdbcTemplate.update(sql, new Object[] {
			office.getOfficeId(),
			office.getName()
		});
	}
	
	/*
	 * 查询信息
	 * @see OfficeDaoImp#selectByID(String)
	 */
	public Office selectByID(String id) {
		String sql = "select * from office where id=?";
		return jdbcTemplate.queryForObject(sql,
				BeanPropertyRowMapper.newInstance(Office.class), id);
	}
	
	/*
	 * 更新信息
	 * @see OfficeDaoImp#update(Office)
	 */
	public void update(Office office) {
		String sql = "update office set name=? where id=?";
		// 还缺少医生信息和队列信息
		jdbcTemplate.update(sql,
				office.getName(),
				office.getOfficeId());
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public JdbcTemplate getJbdcTemplate() {
		return jdbcTemplate;
	}
	
}
