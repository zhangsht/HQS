package com.iQueue.dao;

import java.util.List;

import com.iQueue.model.Clinic;

public interface ClinicDaoImp {
	public List<Clinic> getClinics(String officeId);
}
