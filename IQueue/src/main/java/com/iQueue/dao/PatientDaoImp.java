package com.iQueue.dao;

import java.util.List;
import com.iQueue.model.PatientInfo;
import com.iQueue.model.SignInfo;

public interface PatientDaoImp {
	
	public void insert(PatientInfo patient, SignInfo signInfo);
	public void delete(String id);
	public void update(PatientInfo patient);
	public PatientInfo selectByID(String id);
	public List<PatientInfo> find();
}
