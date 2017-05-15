package com.iQueue.dao;

import java.util.List;
import com.iQueue.model.DoctorInfo;

public interface DoctorDaoImp {
	public void insert(DoctorInfo doctor);
	public void delete(String id);
	public void update(DoctorInfo doctor);
	public DoctorInfo selectByEmpID(String id);
	public List find();
}
