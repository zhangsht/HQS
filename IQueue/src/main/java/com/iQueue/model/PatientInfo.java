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
	private String pId;  // �Һ����к�
	private String name;  // ��������
	private String cardNumber; // ���˾����к�
	
	private String sex;  // �����Ա�
	private String age;  // ��������
	private String registerTime; // ���˹Һ�ʱ��
	private String arriveTime;  // ���˵����ҷ��ﻤʿվ�ı���ʱ��
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
