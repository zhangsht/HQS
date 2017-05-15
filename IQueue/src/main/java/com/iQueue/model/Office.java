package com.iQueue.model;

import java.util.List;

import com.iQueue.model.DoctorInfo;
import com.iQueue.model.QueueInfo;

public class Office {
	private String oId;  // ’Ô “ID
	private String name;	//’Ô “√˚◊÷
	private String clinicsNames;
	
	public String getoId() {
		return oId;
	}
	public void setoId(String oId) {
		this.oId = oId;
	}
	public String getClinicsNames() {
		return clinicsNames;
	}
	public void setClinicsNames(String clinicsNames) {
		this.clinicsNames = clinicsNames;
	}
	public String getFirTreats() {
		return firTreats;
	}
	public void setFirTreats(String firTreats) {
		this.firTreats = firTreats;
	}
	public String getTwiTreats() {
		return twiTreats;
	}
	public void setTwiTreats(String twiTreats) {
		this.twiTreats = twiTreats;
	}
	public String getTriTreats() {
		return triTreats;
	}
	public void setTriTreats(String triTreats) {
		this.triTreats = triTreats;
	}
	private String firTreats;
	private String twiTreats;
	private String triTreats;
	
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
