package com.iQueue.model;

public class PatientInfo {
	public PatientInfo() {
		super();
	}

	public PatientInfo(String pId, String name, String cardNumber, String sex, String age, String registerTime,
			String arriveTime, String qId) {
		super();
		this.pId = pId;
		this.name = name;
		this.cardNumber = cardNumber;
		this.sex = sex;
		this.age = age;
		this.registerTime = registerTime;
		this.arriveTime = arriveTime;
		this.qId = qId;
	}
	private String pId;  // 挂号序列号
	private String name;  // 病人姓名
	private String cardNumber; // 病人就序卡列号
	
	private String sex;  // 病人性别
	private String age;  // 病人年龄
	private String registerTime; // 病人挂号时间
	private String arriveTime;  // 病人到科室分诊护士站的报道时间
	private String qId;
//	private String oId;
	
	
	public String getpId() {
		return pId;
	}
	
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	public String getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}
	public String getqId() {
		return qId;
	}
	public void setqId(String qId) {
		this.qId = qId;
	}
	/*public String getoId() {
		return oId;
	}
	public void setoId(String oId) {
		this.oId = oId;
	}*/
	
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
	
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
}
