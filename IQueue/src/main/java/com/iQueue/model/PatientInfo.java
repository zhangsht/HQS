package com.iQueue.model;

import java.util.Date;

public class PatientInfo {
	private String pId;  // 挂号序列号
	private String name;  // 病人姓名
	private String cardNumber; // 病人就序卡列号
	private String qId;
	private String dId;  // 给病人看病的医生信息
	private Sex sex;  // 病人性别
	private String age;  // 病人年龄
	private Date regTime; // 病人挂号时间
	private Date arrTime;  // 病人到科室分诊护士站的报道时间
	private String sId;  // 病人体征信息
	
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
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
	
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	
	public Date getArrTime() {
		return arrTime;
	}
	public void setArrTime(Date arrTime) {
		this.arrTime = arrTime;
	}
	
	public String getsId() {
		return sId;
	}
	public void setsId(String sId) {
		this.sId = sId;
	}
	
	public String getdId() {
		return dId;
	}
	public void setdId(String dId) {
		this.dId = dId;
	}
}
