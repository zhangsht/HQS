package com.iQueue.model;

import java.util.Date;

public class PatientInfo {
	private String pId;  // �Һ����к�
	private String name;  // ��������
	private String cardNumber; // ���˾����к�
	
	private Sex sex;  // �����Ա�
	private int age;  // ��������
	private Date registerTime; // ���˹Һ�ʱ��
	private Date arriveTime;  // ���˵����ҷ��ﻤʿվ�ı���ʱ��
	private String sId;  // ����������Ϣ
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
	
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public Date getRegTime() {
		return registerTime;
	}
	public void setRegTime(Date regTime) {
		this.registerTime = regTime;
	}
	
	public Date getArrTime() {
		return arriveTime;
	}
	public void setArrTime(Date arrTime) {
		this.arriveTime = arrTime;
	}
	
	public String getsId() {
		return sId;
	}
	public void setsId(String sId) {
		this.sId = sId;
	}
}
