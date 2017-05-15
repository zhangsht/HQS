package com.iQueue.dao;

import com.iQueue.model.PatientInfo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import com.iQueue.model.Sex;

public class PatientDao {
	
	private JdbcTemplate jdbcTemplate;
	
	/*
	 * ͨ��RowMapper��ֵ��patientInfo
	 * RowMapper���Խ������е�ÿһ�з�װ���û��������
	 * �����ݿ��ѯ�У�������ص��������û��Զ������������Ҫ��װ
	 */
	/*private static final class PatientMapper implements RowMapper {
		
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			PatientInfo patientInfo = new PatientInfo();
			patientInfo.setID(rs.getString("id"));
			patientInfo.setName(rs.getString("name"));
			patientInfo.setAge(rs.getString("age"));
			patientInfo.setCardNum(rs.getString("cardnum"));
			patientInfo.setReg_Time(rs.getString("regtime"));
			patientInfo.setVis_Time(rs.getString("vistime"));
			patientInfo.setSex(Sex.valueOf(rs.getString("sex")));
			patientInfo.setPatient_SignInfo(rs.getString("height"), rs.getString("weight"), 
					rs.getString("temperature"), rs.getString("respiration"), rs.getString("pulse"),
					rs.getString("blood_pressure"), rs.getString("disease_description"), rs.getString("blood_sugar"));
			return patientInfo;
		}
	}*/
	
	/*
	 * ��ѯ���еĲ���������Ϣ
	 * @see PatientDaoImp#find()
	 */
	/*public List find() {
		String sql = "select * from patient";
		return jdbcTemplate.query(sql, new PatientMapper());
	}
	*/
	/*
	 * ɾ����Ϣ
	 * @see PatientDaoImp#delete(String)
	 */
	public void delete(String id) {
		String sql = "delete from patient where id=?";
		jdbcTemplate.update(sql, id);
	}
	
	/*
	 * ������Ϣ
	 * @see PatientDaoImp#insert(PatientInfo)
	 */
	/*public void insert(PatientInfo patientInfo) {
		// ��ȱ��SignInfo
		String sql = "insert into patient (id,name,age,cardnum,regtime,vistime,sex,height,weight,temperature,respiration,pulse,blood_pressure,disease_description,blood_sugar) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, new Object[] {
			patientInfo.getID(),
			patientInfo.getName(),
			patientInfo.getAge(),
			patientInfo.getCardNum(),
			patientInfo.getReg_Time(),
			patientInfo.getVis_Time(),
			patientInfo.getSex().name(),
			patientInfo.getPatient_SignInfo_Height(),
			patientInfo.getPatient_SignInfo_Weight(),
			patientInfo.getPatient_SignInfo_Temperature(),
			patientInfo.getPatient_SignInfo_Respiration(),
			patientInfo.getPatient_SignInfo_Pulse(),
			patientInfo.getPatient_SignInfo_BloodPressure(),
			patientInfo.getPatient_SignInfo_Disease(),
			patientInfo.getPatient_SignInfo_BloodSugar()
		});
	}*/
	
	/*
	 * ��ѯ��Ϣ
	 * @see PatientDaoImp#selectByID(String)
	 */
	public PatientInfo selectByID(String id) {
		String sql = "select * from patient where id=?";
		return jdbcTemplate.queryForObject(sql,
				BeanPropertyRowMapper.newInstance(PatientInfo.class), id);
	}
	
	/*
	 * ������Ϣ
	 * @see PatientDaoImp#update(PatientInfo)
	 */
	/*public void update(PatientInfo patientInfo) {
		String sql = "update patient set name=?,age=?,cardnum=?,regtime=?,vistime=?,sex=?,height=?,weight=?,temperature=?,respiration=?,pulse=?,blood_pressure=?,disease_description=?,blood_sugar=? where id=?";
		// ��ȱ��SignInfo
		jdbcTemplate.update(sql,
				patientInfo.getName(),
				patientInfo.getAge(),
				patientInfo.getCardNum(),
				patientInfo.getReg_Time(),
				patientInfo.getVis_Time(),
				patientInfo.getSex().name(),
				patientInfo.getPatient_SignInfo_Height(),
				patientInfo.getPatient_SignInfo_Weight(),
				patientInfo.getPatient_SignInfo_Temperature(),
				patientInfo.getPatient_SignInfo_Respiration(),
				patientInfo.getPatient_SignInfo_Pulse(),
				patientInfo.getPatient_SignInfo_BloodPressure(),
				patientInfo.getPatient_SignInfo_Disease(),
				patientInfo.getPatient_SignInfo_BloodSugar(),
				patientInfo.getID());
	}*/
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public JdbcTemplate getJbdcTemplate() {
		return jdbcTemplate;
	}
	
}
