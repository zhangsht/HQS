package com.iQueue.model;

import java.util.Date;

public class PatientInfo {
	private String pId;  // �Һ����к�
	private String name;  // ��������
	private String cardNumber; // ���˾����к�
	private String qId;
	private String dId;  // �����˿�����ҽ����Ϣ
	private Sex sex;  // �����Ա�
	private String age;  // ��������
	private Date regTime; // ���˹Һ�ʱ��
	private Date arrTime;  // ���˵����ҷ��ﻤʿվ�ı���ʱ��
	private String sId;  // ����������Ϣ
	
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
