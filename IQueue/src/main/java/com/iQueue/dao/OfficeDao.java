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
	 * ͨ��RowMapper��ֵ��office
	 * RowMapper���Խ������е�ÿһ�з�װ���û��������
	 * �����ݿ��ѯ�У�������ص��������û��Զ������������Ҫ��װ
	 */
	private static final class OfficeMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			// ȱ��ҽ����Ϣ�Ͷ�����Ϣ
			Office office = new Office();
			office.setOId(rs.getString("id"));
			office.setName(rs.getString("name"));
			return office;
		}
	}
	
	/*
	 * ��ѯ���е�����������Ϣ
	 * @see OfficeDaoImp#find()
	 */
	public List find() {
		String sql = "select * from office";
		return jdbcTemplate.query(sql, new OfficeMapper());
	}
	
	/*
	 * ɾ����Ϣ
	 * @see OfficeDaoImp#delete(String)
	 */
	public void delete(String id) {
		String sql = "delete from office where id=?";
		jdbcTemplate.update(sql, id);
	}
	
	/*
	 * ������Ϣ
	 * @see OfficeDaoImp#insert(Office)
	 */
	public void insert(Office office) {
		// ��ȱ��ҽ����Ϣ�Ͷ�����Ϣ
		String sql = "insert into office (id,name) values(?,?)";
		jdbcTemplate.update(sql, new Object[] {
			office.getOfficeId(),
			office.getName()
		});
	}
	
	/*
	 * ��ѯ��Ϣ
	 * @see OfficeDaoImp#selectByID(String)
	 */
	public Office selectByID(String id) {
		String sql = "select * from office where id=?";
		return jdbcTemplate.queryForObject(sql,
				BeanPropertyRowMapper.newInstance(Office.class), id);
	}
	
	/*
	 * ������Ϣ
	 * @see OfficeDaoImp#update(Office)
	 */
	public void update(Office office) {
		String sql = "update office set name=? where id=?";
		// ��ȱ��ҽ����Ϣ�Ͷ�����Ϣ
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
