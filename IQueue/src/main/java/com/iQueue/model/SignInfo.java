package com.iQueue.model;

/* ����������Ϣ */
public class SignInfo {
	public String sId;
	public String oId;
	public double height;  // ���
	public double weight;  // ����
	public double temperature;  // ����
	public int respiration;  // ����
	public int pulse;  // ����
	public int bloodPressure;  // Ѫѹ
	public double bloodSugar;  // Ѫ��
	public String description;  // ��֢����
	
	
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public int getRespiration() {
		return respiration;
	}
	public void setRespiration(int respiration) {
		this.respiration = respiration;
	}
	public int getPulse() {
		return pulse;
	}
	public void setPulse(int pulse) {
		this.pulse = pulse;
	}
	public int getbPressure() {
		return bloodPressure;
	}
	public void setbPressure(int bPressure) {
		this.bloodPressure = bPressure;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getBsugar() {
		return bloodSugar;
	}
	public void setBsugar(double bsugar) {
		this.bloodSugar = bsugar;
	}
}
