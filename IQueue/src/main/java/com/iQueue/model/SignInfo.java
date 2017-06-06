package com.iQueue.model;

/* 病人体征信息 */
public class SignInfo {
	public String sId;
	public String oId;
	public double height;  // 身高
	public double weight;  // 体重
	public double temperature;  // 体温
	public int respiration;  // 呼吸
	public int pulse;  // 脉搏
	public int bloodPressure;  // 血压
	public double bloodSugar;  // 血糖
	public String description;  // 病症描述
	
	
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
