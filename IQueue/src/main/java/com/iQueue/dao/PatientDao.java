package com.iQueue.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.RowSet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.iQueue.model.PatientInfo;
import com.iQueue.model.SignInfo;
import com.iQueue.model.User;
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
			patientInfo.setpId(rs.getString("pId"));
			patientInfo.setName(rs.getString("name"));
			patientInfo.setArriveTime(rs.getString("arriveTime"));
			return patientInfo;
		}
	}
	
	private static final class QueueIdMapper implements RowMapper<PatientInfo> {
		public PatientInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			PatientInfo patientInfo = new PatientInfo();
			patientInfo.setqId(rs.getString("qId"));
			return patientInfo;
		}
	}

	public List<PatientInfo> getQueues(String queueId) {
		String SQL = "select pId, name, arriveTime from patientInfo where qId = ? order by arriveTime desc limit 10";
		List<PatientInfo> items = jdbcTemplate.query(SQL, new Object[] { queueId }, new QueueMapper());
		return items;
	}

	public List<Map<String, Object>> getAllPatient() {
		String sql = "select * from patientInfo";
		List<Map<String, Object>> items1 = new ArrayList<Map<String, Object>>();
		items1 = jdbcTemplate.queryForList(sql);

		String sql2 = "select * from signinfo";
		List<Map<String, Object>> items2 = new ArrayList<Map<String, Object>>();
		items2 = jdbcTemplate.queryForList(sql2);
		List<Map<String, Object>> itemsList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < items1.size() && i < items2.size(); i++) {
			itemsList.add(items1.get(i));
			itemsList.add(items2.get(i));
		}
		return itemsList;
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
		String sql = "update patientInfo set qId=? where pId=?";
		jdbcTemplate.update(sql, newQueueId, name);
	}

	public String checkInfo(String pId, String name) {
		String SQL = "select qId from patientInfo where pId = ? or name = ?";
		List<PatientInfo> items = jdbcTemplate.query(SQL, new Object[] { pId, name }, new QueueIdMapper());
		if (items.isEmpty()) {
			return "null";
		}
		String qId = items.get(0).getqId();
		
		String result = "officeInfo: ";
		OfficeDao officeDao = new OfficeDao();
		String officeInfo = officeDao.queryQueueInfo(qId);
		if (officeInfo != null) {
			result += officeInfo;
		}
		
		result += ", doctorInfo: ";
		DoctorDao doctorDao = new DoctorDao();
		String doctorInfo = doctorDao.queryQueueInfo(qId);
		if (doctorInfo != null) {
			result += doctorInfo;
		}
		return result;
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

	public void deleteOverTime(String endTime) {
		String sql = "delete from patientInfo where arriveTime >= ?";
		jdbcTemplate.update(sql, endTime);
	}

	public void updateQueue(String preTreatId, String pId) {
		String sql = "update patientInfo set qId = ? where pId =?";
		jdbcTemplate.update(sql, preTreatId, pId);
	}
}
