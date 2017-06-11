package com.iQueue.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.iQueue.model.DoctorInfo;
import com.iQueue.service.DataSourceUtills;

public class DoctorDao implements DoctorDaoImp {
	private JdbcTemplate jdbcTemplate;

	public DoctorDao() {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		DataSourceUtills dataSourceUtills = (DataSourceUtills) context.getBean("dataSourceUtils");
		jdbcTemplate = dataSourceUtills.getJbdcTemplate();
	}
	
	private static final class DoctorInfoMapper implements RowMapper<DoctorInfo> {
		public DoctorInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			DoctorInfo doctorInfo = new DoctorInfo();
			doctorInfo.setdId(rs.getString("dId"));
			doctorInfo.setName(rs.getString("name"));
			doctorInfo.setcId(rs.getString("cId"));
			doctorInfo.setProfile(rs.getString("profile"));
			doctorInfo.setStartTime(rs.getString("startTime"));
			doctorInfo.setEndTime(rs.getString("endTime"));
			doctorInfo.setPreTreatId(rs.getString("preTreatId"));
			doctorInfo.setInTreatId(rs.getString("inTreatId"));
			doctorInfo.setAfterTreatId(rs.getString("afterTreatId"));
			return doctorInfo;
		}
	}
	
	private static final class NameInfoMapper implements RowMapper<DoctorInfo> {
		public DoctorInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			DoctorInfo doctorInfo = new DoctorInfo();
			doctorInfo.setName(rs.getString("name"));
			return doctorInfo;
		}
	}

	public DoctorInfo getDoctorInfo(String clinicId) {
		String SQL = "select * from doctorinfo where cId = ?";
		List<DoctorInfo> items = jdbcTemplate.query(SQL, new Object[] { clinicId }, new DoctorInfoMapper());
		if (items.isEmpty()) {
			return null;
		}
		return items.get(0);
	}

	public void updateInfo(String clinicId, String doctorName, String profile, String startTime, String endTime) {
		String sql = "update doctorinfo set name = ?, profile = ?, startTime = ?, endTime = ? "
				+ "where cId = ?";
		jdbcTemplate.update(sql, doctorName, profile, startTime, endTime, clinicId);
	}

	public String queryQueueInfo(String qId) {
		String SQL = "select * from doctorinfo where preTreatId = ?";
		List<DoctorInfo> items = jdbcTemplate.query(SQL, new Object[] { qId }, new DoctorInfoMapper());
		if (items != null && items.size() > 0) {
			return "doctorName: " + items.get(0).getName() + " 队列: " + "候诊队列";
		}
		String SQL1 = "select * from doctorinfo where inTreatId = ?";
		List<DoctorInfo> items1 = jdbcTemplate.query(SQL1, new Object[] { qId }, new DoctorInfoMapper());
		if (items1 != null && items.size() > 0) {
			return "doctorName: " + items1.get(0).getName() + " 队列: " + "就诊队列";
		}
		String SQL2 = "select * from doctorinfo where afterTreatId = ?";
		List<DoctorInfo> items2 = jdbcTemplate.query(SQL2, new Object[] { qId }, new DoctorInfoMapper());
		if (items2 != null && items.size() > 0) {
			return "doctorName: " + items2.get(0).getName() + " 队列: " + "完诊队列";
		}
		return "null";
	}
}
