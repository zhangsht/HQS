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
	 * 通过RowMapper赋值给patientInfo
	 * RowMapper可以将数据中的每一行封装成用户定义的类
	 * 在数据库查询中，如果返回的类型是用户自定义的类型则需要包装
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
	 * 查询所有的病人数据信息
	 * @see PatientDaoImp#find()
	 */
	/*public List find() {
		String sql = "select * from patient";
		return jdbcTemplate.query(sql, new PatientMapper());
	}
	*/
	/*
	 * 删除信息
	 * @see PatientDaoImp#delete(String)
	 */
	public void delete(String id) {
		String sql = "delete from patient where id=?";
		jdbcTemplate.update(sql, id);
	}
	
	/*
	 * 插入信息
	 * @see PatientDaoImp#insert(PatientInfo)
	 */
	/*public void insert(PatientInfo patientInfo) {
		// 还缺少SignInfo
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
	 * 查询信息
	 * @see PatientDaoImp#selectByID(String)
	 */
	public PatientInfo selectByID(String id) {
		String sql = "select * from patient where id=?";
		return jdbcTemplate.queryForObject(sql,
				BeanPropertyRowMapper.newInstance(PatientInfo.class), id);
	}
	
	/*
	 * 更新信息
	 * @see PatientDaoImp#update(PatientInfo)
	 */
	/*public void update(PatientInfo patientInfo) {
		String sql = "update patient set name=?,age=?,cardnum=?,regtime=?,vistime=?,sex=?,height=?,weight=?,temperature=?,respiration=?,pulse=?,blood_pressure=?,disease_description=?,blood_sugar=? where id=?";
		// 还缺少SignInfo
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
