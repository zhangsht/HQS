package com.iQueue.model;

import java.util.Date;
import java.util.Queue;
import com.iQueue.model.PatientInfo;

public class DoctorInfo {
	private String dId;  // ҽ������
	private String name;  // ҽ������
	private String cId;
	private String profile;  // ҽ�����
	private Date startTime;  // ҽ����ʼ����ʱ��
	private Date endTime;  // ҽ����������ʱ��
	private String preTreatId;
	private String inTreatId;
	private String afterTreatId;
	
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
	
	public String getPreTreatId() {
		return preTreatId;
	}
	public void setPreTreatId(String preTreatId) {
		this.preTreatId = preTreatId;
	}
	
	public String getInTreatId() {
		return inTreatId;
	}
	public void setInTreatId(String inTreatId) {
		this.inTreatId = inTreatId;
	}
	
	public String getAfterTreatId() {
		return afterTreatId;
	}
	public void setAfterTreatId(String afterTreatId) {
		this.afterTreatId = afterTreatId;
	}
}
