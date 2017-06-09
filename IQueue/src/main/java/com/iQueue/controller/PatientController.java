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
		String opcode = request.getParameter("opcode");
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("opcode", opcode);
		if (opcode.equals("change")) {
			PatientDao patientDao = new PatientDao();
			String newQueueId = request.getParameter("newQueueId");
			String name = request.getParameter("name");
			patientDao.changeQueue(newQueueId, name);
			modelMap.put("status", "success");
		} else {
			modelMap.put("status", "fail");
		}
		;
		return modelMap;
	}
}
