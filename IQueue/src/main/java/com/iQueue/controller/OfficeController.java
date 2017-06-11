package com.iQueue.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iQueue.dao.ClinicDao;
import com.iQueue.dao.DoctorDao;
import com.iQueue.dao.OfficeDao;
import com.iQueue.dao.PatientDao;
import com.iQueue.model.Clinic;
import com.iQueue.model.DoctorInfo;
import com.iQueue.model.Office;
import com.iQueue.model.PatientInfo;
import com.iQueue.model.SignInfo;

@Controller
public class OfficeController {
	@RequestMapping(value = "/getClinicList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> initOffice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			String opcode = request.getParameter("opcode");
			String officeId = request.getParameter("officeId");
			if (opcode.equals("getClinicList")) {
				OfficeDao officeDao = new OfficeDao();
				String officeName = officeDao.getOfficeName(officeId);
				ClinicDao clinicDao = new ClinicDao();
				List<Clinic> clinicList = clinicDao.getClinics(officeId);
				System.out.println(clinicList.size());
				modelMap.put("officeName", officeName);
				modelMap.put("clinicList", clinicList);
				modelMap.put("status", "success");
			} else {
				modelMap.put("status", "fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelMap;
	}

	@RequestMapping(value = "/oldAdd", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addPatient(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			String opcode = request.getParameter("opcode");
			String officeId = request.getParameter("officeId");
			if (opcode.equals("addPatient")) {
				modelMap.put("status", "success");
				ClinicDao clinicDao = new ClinicDao();
				List<Clinic> clinicList = clinicDao.getClinics(officeId);
				System.out.println(clinicList.size());
				modelMap.put("clinicList", clinicList);
			} else {
				modelMap.put("status", "fail");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return modelMap;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addNewComer(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			String opcode = request.getParameter("opcode");
			if (opcode.equals("add")) {
				String pId = request.getParameter("pId");
				//String qId = request.getParameter("qId");
				String name = request.getParameter("name");
				System.out.println("name is " + name);
				String cardNumber = request.getParameter("cardNumber");
				String sex = request.getParameter("sex");
				String age = request.getParameter("age");
				String registerTime = request.getParameter("registerTime");
				String arriveTime = request.getParameter("arriveTime");

				String height = request.getParameter("height");
				String weight = request.getParameter("weight");
				String temperature = request.getParameter("temperature");
				String respiration = request.getParameter("respiration");
				String pulse = request.getParameter("pulse");
				String bloodPressure = request.getParameter("bloodPressure");
				String bloodSugar = request.getParameter("bloodSugar");
				String description = request.getParameter("description");

				PatientInfo patientInfo = new PatientInfo(pId, name, cardNumber, sex, age, registerTime, arriveTime, "123");
				SignInfo signInfo = new SignInfo(pId, height, weight, temperature, respiration, pulse,
						bloodPressure, bloodSugar, description);
				PatientDao patientDao = new PatientDao();
				patientDao.insert(patientInfo, signInfo);
				modelMap.put("status", "success");
				modelMap.put("patientInfo", patientInfo);
				modelMap.put("signInfo", signInfo);
			} else {
				modelMap.put("status", "fail");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return modelMap;
	}
	
	@RequestMapping(value = "/initPatientInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> initPatientInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			String opcode = request.getParameter("opcode");
			if (opcode.equals("initPatientInfo")) {
				PatientDao patientDao = new PatientDao();
				List<Map<String, Object>> datasList = patientDao.getAllPatient();
				modelMap.put("patientInfo", datasList);
				modelMap.put("status", "success");
			} else {
				modelMap.put("status", "fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelMap;
	}

	@RequestMapping(value = "/next", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> nextOne(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			String opcode = request.getParameter("opcode");
			modelMap.put("opcode", "next");
			if (opcode.equals("next")) {
				modelMap.put("status", "success");
			} else {
				modelMap.put("status", "fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelMap;
	}

	@RequestMapping(value = "/officeQueue", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> officeQueue(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			String opcode = request.getParameter("opcode");
			String officeId = request.getParameter("officeId");
			if (opcode.equals("officeQueue")) {
				modelMap.put("status", "success");
				OfficeDao officeDao = new OfficeDao();
				Office office = officeDao.getOffice(officeId);
				if (office != null) {
					modelMap.put("officeInfo", office);
					String firTreatId = office.getFirTreatId();
					String secTreatId = office.getSecTreatId();
					String dispatchTreatId = office.getDispatchTreatId();
					PatientDao patientDao = new PatientDao();
					List<PatientInfo> firTreatList = patientDao.getQueues(firTreatId);
					List<PatientInfo> secTreatList = patientDao.getQueues(secTreatId);
					List<PatientInfo> dispatchTreatList = patientDao.getQueues(dispatchTreatId);
					modelMap.put("firstQueue", firTreatList);
					modelMap.put("secondQueue", secTreatList);
					modelMap.put("triageQueue", dispatchTreatList);
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
	
	
	@RequestMapping(value = "/updateOfficeInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateOfficeInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			String opcode = request.getParameter("opcode");
			if (opcode != null && opcode.equals("updateOfficeInfo")) {
				String newName = request.getParameter("newName");
				String officeId = request.getParameter("officeId");
				OfficeDao officeDao = new OfficeDao();
				officeDao.updateName(officeId, newName);
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
