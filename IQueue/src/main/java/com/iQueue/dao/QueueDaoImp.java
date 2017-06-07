package com.iQueue.dao;

import java.util.List;

import com.iQueue.model.Clinic;
import com.iQueue.model.Queue;

public interface QueueDaoImp {
	public List<Queue> getQueues(String queueID);
}
