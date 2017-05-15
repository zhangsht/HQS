package com.iQueue.dao;

import java.util.List;
import com.iQueue.model.Office;

public interface OfficeDaoImp {
	public void insert(Office office);
	public void delete(String id);
	public void update(Office office);
	public Office selectByID(String id);
	public List find();
}
