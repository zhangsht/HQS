package com.iQueue.dao;

import java.util.List;
import com.iQueue.model.PatientInfo;

public interface PatientDaoImp {
	
	public void insert(PatientInfo patient);
	public void delete(String id);
	public void update(PatientInfo patient);
	public PatientInfo selectByID(String id);
	public List find();
}
