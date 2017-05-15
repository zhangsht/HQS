package com.iQueue.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.iQueue.model.PatientInfo;
import com.iQueue.model.DoctorInfo;

public class DoctorDao {

	private JdbcTemplate jdbcTemplate;
	
	/*
	 * 通过RowMapper赋值给doctorInfo
	 * RowMapper可以将数据中的每一行封装成用户定义的类
	 * 在数据库查询中，如果返回的类型是用户自定义的类型则需要包装
	 */
	/*private static final class DoctorMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			// 缺少三个队列的封装
			DoctorInfo doctorInfo = new DoctorInfo();
			doctorInfo.setEmpID(rs.getString("empid"));
			doctorInfo.setIntroduction(rs.getString("intro"));
			doctorInfo.setName(rs.getString("name"));
			doctorInfo.setOffice(rs.getString("office"));
			doctorInfo.setStartTime(rs.getString("starttime"));
			doctorInfo.setEndTime(rs.getString("endtime"));
			return doctorInfo;
		}
	}*/
	
	/*
	 * 查询所有的医生数据信息
	 * @see DoctorDaoImp#find()
	 */
/*	public List find() {
		String sql = "select * from doctor";
		return jdbcTemplate.query(sql, new DoctorMapper());
	}
	*/
	/*
	 * 删除信息
	 * @see DoctorDaoImp#delete(String)
	 */
	public void delete(String id) {
		String sql = "delete from doctor where empid=?";
		jdbcTemplate.update(sql, id);
	}
	
	/*
	 * 插入信息
	 * @see DoctorDaoImp#insert(DoctorInfo)
	 */
	/*public void insert(DoctorInfo doctorInfo) {
		// 还缺少三个队列
		String sql = "insert into doctor (empid,name,intro,office,starttime,endtime) values(?,?,?,?,?,?)";
		jdbcTemplate.update(sql, new Object[] {
			doctorInfo.getEmpID(),
			doctorInfo.getName(),
			doctorInfo.getIntroduction(),
			doctorInfo.getOffice(),
			doctorInfo.getStartTime(),
			doctorInfo.getEndTime()
		});
	}*/
	
	/*
	 * 查询信息
	 * @see DoctorDaoImp#selectByEmpID(String)
	 */
	public DoctorInfo selectByEmpID(String id) {
		String sql = "select * from doctor where empid=?";
		return jdbcTemplate.queryForObject(sql,
				BeanPropertyRowMapper.newInstance(DoctorInfo.class), id);
	}
	
	/*
	 * 更新信息
	 * @see DoctorDaoImp#update(DoctorInfo)
	 */
	/*public void update(DoctorInfo doctorInfo) {
		String sql = "update doctor set name=?,intro=?,office?,starttime=?,endtime=? where empid=?";
		// 还缺少三个队列
		jdbcTemplate.update(sql,
				doctorInfo.getName(),
				doctorInfo.getIntroduction(),
				doctorInfo.getOffice(),
				doctorInfo.getStartTime(),
				doctorInfo.getEndTime(),
				doctorInfo.getEmpID());
	}*/
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public JdbcTemplate getJbdcTemplate() {
		return jdbcTemplate;
	}
	
}
