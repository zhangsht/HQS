package com.iQueue.controller;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iQueue.dao.PatientDao;

@Controller
public class PatientController {
	@RequestMapping(value = "/change", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> nextOne(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			String opcode = request.getParameter("opcode");
			if (opcode.equals("change")) {
				PatientDao patientDao = new PatientDao();
				String newQueueId = request.getParameter("newQueueId");
				String name = request.getParameter("name");
				patientDao.changeQueue(newQueueId, name);
				modelMap.put("status", "success");
			} else {
				modelMap.put("status", "fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelMap;
	}
	
	@RequestMapping(value="/checkPatientQueue", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkPatientQueue(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			String opcode = request.getParameter("opcode");
			if (opcode.equals("checkPatientQueue")) {
				PatientDao patientDao = new PatientDao();
				String pId = request.getParameter("pId");
				pId = pId == null ? "" : pId;
				String name = request.getParameter("name");
				name = name == null ? "" : name;
				modelMap.put("queueInfo", patientDao.checkInfo(pId, name));
				
				modelMap.put("status", "success");
			} else {
				modelMap.put("status", "fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelMap;
	}
}
