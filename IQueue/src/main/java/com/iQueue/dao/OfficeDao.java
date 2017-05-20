package com.iQueue.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.iQueue.model.Office;

public class OfficeDao implements OfficeDaoImp {
	
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	
	/*
	 * ͨ��RowMapper��ֵ��office
	 * RowMapper���Խ������е�ÿһ�з�װ���û��������
	 * �����ݿ��ѯ�У�������ص��������û��Զ������������Ҫ��װ
	 */
	private static final class OfficeMapper implements RowMapper<Office> {
		public Office mapRow(ResultSet rs, int rowNum) throws SQLException {
			// ȱ��ҽ����Ϣ�Ͷ�����Ϣ
			Office office = new Office();
			office.setOId(rs.getString("o_id"));
			office.setName(rs.getString("name"));
			office.setFirTreatId(rs.getString("firTreatId"));
			office.setTwiTreatId(rs.getString("twiTreatId"));
			office.setTriTreatId(rs.getString("triTreatId"));
			return office;
		}
	}
	
	
	public Office getOffice(String officeId) {
		String SQL = "select * from office where o_id = ?";
		List<Office> items = jdbcTemplate.query(SQL, new Object[] { officeId }, new OfficeMapper());
		if (items.isEmpty()) {
			return null;
		}
		return items.get(0);
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
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public JdbcTemplate getJbdcTemplate() {
		return jdbcTemplate;
	}
	
}
