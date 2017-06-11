package com.iQueue.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.iQueue.model.Office;
import com.iQueue.service.DataSourceUtills;

public class OfficeDao implements OfficeDaoImp {

	private JdbcTemplate jdbcTemplate;

	public OfficeDao() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		DataSourceUtills dataSourceUtills = (DataSourceUtills) context.getBean("dataSourceUtils");
		jdbcTemplate = dataSourceUtills.getJbdcTemplate();
	}

	/*
	 * 通过RowMapper赋值给office RowMapper可以将数据中的每一行封装成用户定义的类
	 * 在数据库查询中，如果返回的类型是用户自定义的类型则需要包装
	 */
	private static final class OfficeMapper implements RowMapper<Office> {
		public Office mapRow(ResultSet rs, int rowNum) throws SQLException {
			Office office = new Office();
			office.setoId(rs.getString("o_id"));
			office.setName(rs.getString("name"));
			office.setFirTreatId(rs.getString("firTreatId"));
			office.setSecTreatId(rs.getString("secTreatId"));
			office.setDispatchTreatId(rs.getString("dispatchTreatId"));
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

	public String getOfficeName(String officeId) {
		String sql = "select * from office where o_id = ?";
		Office office = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Office.class), officeId);
		return office.getName();
	}

	public void updateName(String officeId, String newName) {
		String sql = "update office set name =? where o_id=?";
		jdbcTemplate.update(sql, newName, officeId);
	}

	/*
	 * 查询所有的诊室数据信息
	 * 
	 * @see OfficeDaoImp#find()
	 */
	public List find() {
		String sql = "select * from office";
		return jdbcTemplate.query(sql, new OfficeMapper());
	}

	/*
	 * 删除信息
	 * 
	 * @see OfficeDaoImp#delete(String)
	 */
	public void delete(String id) {
		String sql = "delete from office where id=?";
		jdbcTemplate.update(sql, id);
	}

	/*
	 * 插入信息
	 * 
	 * @see OfficeDaoImp#insert(Office)
	 */
	public void insert(Office office) {
		// 还缺少医生信息和队列信息
		String sql = "insert into office (id,name) values(?,?)";
		jdbcTemplate.update(sql, new Object[] { office.getoId(), office.getName() });
	}

	/*
	 * 查询信息
	 * 
	 * @see OfficeDaoImp#selectByID(String)
	 */
	public Office selectByID(String id) {
		String sql = "select * from office where id=?";
		return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Office.class), id);
	}

	/*
	 * 更新信息
	 * 
	 * @see OfficeDaoImp#update(Office)
	 */
	public void update(Office office) {
		String sql = "update office set name=? where id=?";
		// 还缺少医生信息和队列信息
		jdbcTemplate.update(sql, office.getName(), office.getoId());
	}

	public String queryQueueInfo(String qId) {
		String SQL = "select * from office where firTreatId = ?";
		List<Office> items = jdbcTemplate.query(SQL, new Object[] { qId }, new OfficeMapper());
		if (items != null && items.size() > 0) {
			return "officeName: " + items.get(0).getName() + " 队列: " + "首诊队列";
		}
		String SQL1 = "select * from office where secTreatId = ?";
		List<Office> items1 = jdbcTemplate.query(SQL1, new Object[] { qId }, new OfficeMapper());
		if (items1 != null && items.size() > 0) {
			return "officeName: " + items1.get(0).getName() + " 队列: " + "2诊队列";
		}
		String SQL2 = "select * from office where dispatchTreatId = ?";
		List<Office> items2 = jdbcTemplate.query(SQL2, new Object[] { qId }, new OfficeMapper());
		if (items2 != null && items.size() > 0) {
			return "officeName: " + items2.get(0).getName() + " 队列: " + "分诊队列";
		}
		return "null";
	}
}
