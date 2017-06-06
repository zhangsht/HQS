package com.iQueue.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.iQueue.model.Clinic;
import com.iQueue.model.User;
import com.iQueue.service.DataSourceUtills;

public class ClinicDao implements ClinicDaoImp {
	private JdbcTemplate jdbcTemplate;
	
	public ClinicDao() {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		DataSourceUtills dataSourceUtills = (DataSourceUtills) context.getBean("dataSourceUtils");
		jdbcTemplate = dataSourceUtills.getJbdcTemplate();
	}
	
	private static final class ClinicMapper implements RowMapper<Clinic> {
		public Clinic mapRow(ResultSet rs, int rowNum) throws SQLException {
			Clinic clinic = new Clinic();
			clinic.setCId(rs.getString("cId"));
			clinic.setName(rs.getString("name"));
			clinic.setOId(rs.getString("oId"));
			
			return clinic;
		}
	}
	
	public List<Clinic> getClinics(String officeId) {
		String SQL = "select * from clinic where oId = ?";
		List<Clinic> items = jdbcTemplate.query(SQL, new Object[] { officeId }, new ClinicMapper());
		return items;
	}

}
