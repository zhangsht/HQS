package com.iQueue.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.iQueue.dao.PatientDao;
import com.iQueue.model.DoctorInfo;
import com.iQueue.model.PatientInfo;

@Controller
public class ClinicController {
	@RequestMapping(value="/clinicDetail", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> clinicDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			String opcode = request.getParameter("opcode");
			String clinicId = request.getParameter("clinicId");
			
			if (opcode.equals("clinicDetail")) {
				
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
					modelMap.put("status", "success");
				} else {
					modelMap.put("status", "fail");
				}
			} else {
				modelMap.put("status", "fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelMap;
	}
	
	@RequestMapping(value="/updateClinicInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateSignInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			String opcode = request.getParameter("opcode");
			if (opcode.equals("updateClinicInfo")) {
				String clinicId = request.getParameter("clinicId");
				String doctorName = request.getParameter("doctorName");
				String profile = request.getParameter("profile");
				String startTime = request.getParameter("startTime");
				String endTime = request.getParameter("endTime");
				String clinicName = request.getParameter("clinicName");
				DoctorDao doctorDao = new DoctorDao();
				doctorDao.updateInfo(clinicId, doctorName, profile, startTime, endTime);
				if (clinicName != null) {
					ClinicDao clinicDao = new ClinicDao();
					clinicDao.updateName(clinicId, clinicName);
				}
				modelMap.put("status", "success");
			} else {
				modelMap.put("status", "fail");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelMap;
	}
	
	@RequestMapping(value="/deleteOvertime", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteOvertime(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			String opcode = request.getParameter("opcode");
			if (opcode.equals("deleteOvertime")) {
				String clinicId = request.getParameter("clinicId");
				DoctorDao doctorDao = new DoctorDao();
				Date d = new Date();  
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		        String endTime = sdf.format(d);
		        endTime += " ";
		        DoctorInfo doctorInfo = doctorDao.getDoctorInfo(clinicId);
		        if (doctorInfo != null) {
		        	endTime += doctorInfo.getEndTime();
		        }
				PatientDao patientDao = new PatientDao();
				patientDao.deleteOverTime(endTime);
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
