package com.iQueue.model;

public class Office {
	private String oId;
	private String name;
	private String firTreatId;
	private String secTreatId;
	private String dispatchTreatId;
	
	public String getFirTreatId() {
		return firTreatId;
	}
	public void setFirTreatId(String firTreatId) {
		this.firTreatId = firTreatId;
	}
	
	public String getoId() {
		return oId;
	}
	public void setoId(String oId) {
		this.oId = oId;
	}
	public String getSecTreatId() {
		return secTreatId;
	}
	public void setSecTreatId(String secTreatId) {
		this.secTreatId = secTreatId;
	}
	public String getDispatchTreatId() {
		return dispatchTreatId;
	}
	public void setDispatchTreatId(String dispatchTreatId) {
		this.dispatchTreatId = dispatchTreatId;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
