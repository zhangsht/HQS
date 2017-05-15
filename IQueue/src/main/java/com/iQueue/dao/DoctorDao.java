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
	 * ͨ��RowMapper��ֵ��doctorInfo
	 * RowMapper���Խ������е�ÿһ�з�װ���û��������
	 * �����ݿ��ѯ�У�������ص��������û��Զ������������Ҫ��װ
	 */
	/*private static final class DoctorMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			// ȱ���������еķ�װ
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
	 * ��ѯ���е�ҽ��������Ϣ
	 * @see DoctorDaoImp#find()
	 */
/*	public List find() {
		String sql = "select * from doctor";
		return jdbcTemplate.query(sql, new DoctorMapper());
	}
	*/
	/*
	 * ɾ����Ϣ
	 * @see DoctorDaoImp#delete(String)
	 */
	public void delete(String id) {
		String sql = "delete from doctor where empid=?";
		jdbcTemplate.update(sql, id);
	}
	
	/*
	 * ������Ϣ
	 * @see DoctorDaoImp#insert(DoctorInfo)
	 */
	/*public void insert(DoctorInfo doctorInfo) {
		// ��ȱ����������
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
	 * ��ѯ��Ϣ
	 * @see DoctorDaoImp#selectByEmpID(String)
	 */
	public DoctorInfo selectByEmpID(String id) {
		String sql = "select * from doctor where empid=?";
		return jdbcTemplate.queryForObject(sql,
				BeanPropertyRowMapper.newInstance(DoctorInfo.class), id);
	}
	
	/*
	 * ������Ϣ
	 * @see DoctorDaoImp#update(DoctorInfo)
	 */
	/*public void update(DoctorInfo doctorInfo) {
		String sql = "update doctor set name=?,intro=?,office?,starttime=?,endtime=? where empid=?";
		// ��ȱ����������
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
