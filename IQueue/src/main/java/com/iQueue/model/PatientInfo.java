package com.iQueue.model;

import java.util.Date;

public class PatientInfo {
	private String pId;  // 挂号序列号
	private String name;  // 病人姓名
	private String cardNumber; // 病人就序卡列号
	
	private String sex;  // 病人性别
	private int age;  // 病人年龄
	private String registerTime; // 病人挂号时间
	private String arriveTime;  // 病人到科室分诊护士站的报道时间
	private String sId;  // 病人体征信息
	private String qId;
	
	public String getPId() {
		return pId;
	}
	public void setPId(String pId) {
		this.pId = pId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getRegTime() {
		return registerTime;
	}
	public void setRegTime(String regTime) {
		this.registerTime = regTime;
	}
	
	public String getArrTime() {
		return arriveTime;
	}
	public void setArrTime(String arrTime) {
		this.arriveTime = arrTime;
	}
	
	public String getsId() {
		return sId;
	}
	public void setsId(String sId) {
		this.sId = sId;
	}
}
