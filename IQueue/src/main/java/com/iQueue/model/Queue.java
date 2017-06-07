package com.iQueue.model;

import java.util.Date;

public class Queue {
	private String qId;
	private String cId;
	private String name;
	private String time;
	public Queue(String cId, String name, String time, String belong) {
		this.cId = cId;
		this.name = name;
		this.time = time;
		this.qId = belong;
	}
	public String getcId() {
		return cId;
	}
	public void setcId(String cId) {
		this.cId = cId;
	}
	public String getPatientName() {
		return name;
	}
	public void setPatientName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getqId() {
		return qId;
	}
	public void setqId(String qId) {
		this.qId = qId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
