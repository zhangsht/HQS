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
import com.iQueue.model.Clinic;

@Controller
public class OfficeController {
	@RequestMapping(value="/getClinicList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> initOffice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String opcode = request.getParameter("opcode");
		String officeId = request.getParameter("officeId");
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("opcode", "getClinicList");
		if (opcode.equals("getClinicList")) {
			modelMap.put("status", "success");
			ClinicDao clinicDao = new ClinicDao();
			List<Clinic> clinicList = clinicDao.getClinics(officeId);
			System.out.println(clinicList.size());
			modelMap.put("clinicList", clinicList);
		} else {
			modelMap.put("status", "fail");
		};
		return modelMap;
	}
	
	
	@RequestMapping(value="/addPatient", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addPatient(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String opcode = request.getParameter("opcode");
		String officeId = request.getParameter("officeId");
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("opcode", "addPatient");
		if (opcode.equals("addPatient")) {
			modelMap.put("status", "success");
			ClinicDao clinicDao = new ClinicDao();
			List<Clinic> clinicList = clinicDao.getClinics(officeId);
			System.out.println(clinicList.size());
			modelMap.put("clinicList", clinicList);
		} else {
			modelMap.put("status", "fail");
		};
		return modelMap;
	}
	
	@RequestMapping(value="/next", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> nextOne(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String opcode = request.getParameter("opcode");
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("opcode", "next");
		if (opcode.equals("next")) {
			modelMap.put("status", "success");
		} else {
			modelMap.put("status", "fail");
		};
		return modelMap;
	}
}
