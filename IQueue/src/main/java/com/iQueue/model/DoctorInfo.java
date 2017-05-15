package com.iQueue.model;

import java.util.Date;
import java.util.Queue;
import com.iQueue.model.PatientInfo;

public class DoctorInfo {
	private String dId;  // ҽ������
	private String name;  // ҽ������
	private String oId;  // ҽ�����ڿ���
	private String cId;
	private String profile;  // ҽ�����
	private Date startTime;  // ҽ����ʼ����ʱ��
	private Date endTime;  // ҽ����������ʱ��
	private String preTreats;
	private String inTreats;
	private String afterTreats;
	public String getdId() {
		return dId;
	}
	public void setdId(String dId) {
		this.dId = dId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getoId() {
		return oId;
	}
	public void setoId(String oId) {
		this.oId = oId;
	}
	public String getcId() {
		return cId;
	}
	public void setcId(String cId) {
		this.cId = cId;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getPreTreats() {
		return preTreats;
	}
	public void setPreTreats(String preTreats) {
		this.preTreats = preTreats;
	}
	public String getInTreats() {
		return inTreats;
	}
	public void setInTreats(String inTreats) {
		this.inTreats = inTreats;
	}
	public String getAfterTreats() {
		return afterTreats;
	}
	public void setAfterTreats(String afterTreats) {
		this.afterTreats = afterTreats;
	}
}
