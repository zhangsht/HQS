package com.iQueue.model;

import java.util.Date;

public class Queue {
	private String cId;
	private String name;
	private String time;
	private String belong;
	public Queue(String cId, String name, String time, String belong) {
		this.cId = cId;
		this.name = name;
		this.time = time;
		this.belong = belong;
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
	public String getBelong() {
		return belong;
	}
	public void setBelong(String belong) {
		this.belong = belong;
	}
}
