package com.iQueue.model;

import java.util.Date;

public class PatientInfo {
	private String pId;  // �Һ����к�
	private String name;  // ��������
	private String cardNumber; // ���˾����к�
	
	private String sex;  // �����Ա�
	private int age;  // ��������
	private String registerTime; // ���˹Һ�ʱ��
	private String arriveTime;  // ���˵����ҷ��ﻤʿվ�ı���ʱ��
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
