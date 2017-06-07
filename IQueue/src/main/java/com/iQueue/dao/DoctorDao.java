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

	public DoctorInfo getDoctorInfo(String clinicId) {
		String SQL = "select * from doctorinfo where cId = ?";
		List<DoctorInfo> items = jdbcTemplate.query(SQL, new Object[] { clinicId }, new DoctorInfoMapper());
		if (items.isEmpty()) {
			return null;
		}
		return items.get(0);
	}
}
