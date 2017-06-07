package com.iQueue.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iQueue.dao.ClinicDao;
import com.iQueue.dao.DoctorDao;
import com.iQueue.model.Clinic;
import com.iQueue.model.DoctorInfo;
import com.iQueue.model.Queue;

@Controller
public class ClinicController {
	@RequestMapping(value="/clinicDetail", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> initOffice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String opcode = request.getParameter("opcode");
		String officeId = request.getParameter("clinicId");
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("opcode", "clinicDetail");
		if (opcode.equals("clinicDetail")) {
			modelMap.put("status", "success");
			
			DoctorInfo doctorInfo = new DoctorInfo();
			modelMap.put("doctorInfo", doctorInfo);
			List<Queue> preList = null ;
			List<Queue> inList = null;
			List<Queue> afterList = null;
			modelMap.put("preTreat", preList);
			modelMap.put("inTreat", preList);
			modelMap.put("afterTreat", preList);
		} else {
			modelMap.put("status", "fail");
		};
		return modelMap;
	}
}