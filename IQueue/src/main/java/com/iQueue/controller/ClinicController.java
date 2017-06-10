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

import com.iQueue.dao.DoctorDao;
import com.iQueue.dao.PatientDao;
import com.iQueue.model.DoctorInfo;
import com.iQueue.model.PatientInfo;
import com.iQueue.model.Queue;

@Controller
public class ClinicController {
	@RequestMapping(value="/clinicDetail", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> clinicDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String opcode = request.getParameter("opcode");
		String clinicId = request.getParameter("clinicId");
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("opcode", "clinicDetail");
		if (opcode.equals("clinicDetail")) {
			modelMap.put("status", "success");
			DoctorDao doctorDao = new DoctorDao();
			DoctorInfo doctorInfo = doctorDao.getDoctorInfo(clinicId);
			System.out.println(doctorInfo);
			if (doctorInfo != null) {
				modelMap.put("doctorInfo", doctorInfo);
				String preTreatId = doctorInfo.getPreTreatId();
				String inTreatId = doctorInfo.getInTreatId();
				String afterTreatId = doctorInfo.getAfterTreatId();
				
				PatientDao patientDao = new PatientDao();
				List<PatientInfo> preList = patientDao.getQueues(preTreatId) ;
				List<PatientInfo> inList = patientDao.getQueues(inTreatId);
				List<PatientInfo> afterList = patientDao.getQueues(afterTreatId);
				modelMap.put("preTreat", preList);
				modelMap.put("inTreat", inList);
				modelMap.put("afterTreat", afterList);
			} else {
				modelMap.put("status", "fail");
			}
		} else {
			modelMap.put("status", "fail");
		};
		return modelMap;
	}
}
