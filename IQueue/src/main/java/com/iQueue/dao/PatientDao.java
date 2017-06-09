package com.iQueue.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.iQueue.model.PatientInfo;
import com.iQueue.model.SignInfo;
import com.iQueue.service.DataSourceUtills;

public class PatientDao implements PatientDaoImp {

	private JdbcTemplate jdbcTemplate;

	public PatientDao() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		DataSourceUtills dataSourceUtills = (DataSourceUtills) context.getBean("dataSourceUtils");
		jdbcTemplate = dataSourceUtills.getJbdcTemplate();
	}

	private static final class QueueMapper implements RowMapper<PatientInfo> {
		public PatientInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			PatientInfo patientInfo = new PatientInfo();
			patientInfo.setName(rs.getString("name"));
			patientInfo.setArriveTime(rs.getString("arriveTime"));
			return patientInfo;
		}
	}

	public List<PatientInfo> getQueues(String queueId) {
		String SQL = "select name, arriveTime from patientInfo where qId = ? order by arriveTime desc limit 2";
		List<PatientInfo> items = jdbcTemplate.query(SQL, new Object[] { queueId }, new QueueMapper());
		return items;
	}

	public void insert(PatientInfo pf, SignInfo sf) {
		// TODO Auto-generated method stub
		String sql = "insert into patientInfo (pId, name, cardNumber, sex, age, registerTime, arriveTime, qId)"
				+ "values(?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, pf.getpId(), pf.getName(), pf.getCardNumber(), pf.getSex(), pf.getAge(),
				pf.getRegisterTime(), pf.getArriveTime(), pf.getqId());

		if (sf != null) {
			String sql_s = "insert into signinfo (pId, height, weight, temperature, respiration,"
					+ " pulse, bloodPressure, bloodSugar, description)" + "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			jdbcTemplate.update(sql_s, pf.getpId(), sf.getHeight(), sf.getWeight(), sf.getTemperature(),
					sf.getRespiration(), sf.getPulse(), sf.getBloodPressure(), sf.getBloodSugar(), sf.getDescription());
		}
	}

	public void changeQueue(String newQueueId, String name) {
		String sql = "update patientInfo set qId=? where name=?";
		jdbcTemplate.update(sql, newQueueId, name);
	}

	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	public void update(PatientInfo patient) {
		// TODO Auto-generated method stub

	}

	public PatientInfo selectByID(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PatientInfo> find() {
		// TODO Auto-generated method stub
		return null;
	}

}
