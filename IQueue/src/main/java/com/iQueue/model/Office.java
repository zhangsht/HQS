package com.iQueue.model;

public class Office {
	private String oId;
	private String name;
	private String firTreatId;
	private String twiTreatId;
	private String triTreatId;
	
	public String getFirTreatId() {
		return firTreatId;
	}
	public void setFirTreatId(String firTreatId) {
		this.firTreatId = firTreatId;
	}
	
	public String getTwiTreatId() {
		return twiTreatId;
	}
	public void setTwiTreatId(String twiTreatId) {
		this.twiTreatId = twiTreatId;
	}
	
	public String getTriTreatId() {
		return triTreatId;
	}
	public void setTriTreatId(String triTreatId) {
		this.triTreatId = triTreatId;
	}
	
	public void setOId(String Id) {
		this.oId = Id;
	}
	public String getOfficeId() {
		return oId;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
