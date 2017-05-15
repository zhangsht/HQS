package com.iQueue.model;

import java.util.Date;
import java.util.Queue;
import com.iQueue.model.PatientInfo;

public class DoctorInfo {
	private String dId;  // 医生工号
	private String name;  // 医生姓名
	private String oId;  // 医生所在科室
	private String cId;
	private String profile;  // 医生简介
	private Date startTime;  // 医生开始工作时间
	private Date endTime;  // 医生结束工作时间
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
