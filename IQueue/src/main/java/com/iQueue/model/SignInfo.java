package com.iQueue.model;

/* ����������Ϣ */
public class SignInfo {
	
	public SignInfo(String pId, String height, String weight, String temperature, String respiration, String pulse,
			String bloodPressure, String bloodSugar, String description) {
		super();
		this.pId = pId;
		this.height = height;
		this.weight = weight;
		this.temperature = temperature;
		this.respiration = respiration;
		this.pulse = pulse;
		this.bloodPressure = bloodPressure;
		this.bloodSugar = bloodSugar;
		this.description = description;
	}
	public String pId;
	public String height;  // ���
	public String weight;  // ����
	public String temperature;  // ����
	public String respiration;  // ����
	public String pulse;  // ����
	public String bloodPressure;  // Ѫѹ
	public String bloodSugar;  // Ѫ��
	public String description;  // ��֢����
	
	/*public String getsId() {
		return sId;
	}
	public void setsId(String sId) {
		this.sId = sId;
	}*/
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getRespiration() {
		return respiration;
	}
	public void setRespiration(String respiration) {
		this.respiration = respiration;
	}
	public String getPulse() {
		return pulse;
	}
	public void setPulse(String pulse) {
		this.pulse = pulse;
	}
	public String getBloodPressure() {
		return bloodPressure;
	}
	public void setBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
	}
	public String getBloodSugar() {
		return bloodSugar;
	}
	public void setBloodSugar(String bloodSugar) {
		this.bloodSugar = bloodSugar;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
