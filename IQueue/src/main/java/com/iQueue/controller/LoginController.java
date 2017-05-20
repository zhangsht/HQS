package com.iQueue.controller;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PrimitiveIterator.OfInt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iQueue.dao.OfficeDao;
import com.iQueue.dao.UserDao;
import com.iQueue.model.Status;
import com.iQueue.model.User;
import com.iQueue.model.Clinic;
import com.iQueue.model.Office;
import com.iQueue.model.PatientInfo;
 
@Controller
public class LoginController {
	private ApplicationContext context = 
			new ClassPathXmlApplicationContext("applicationContext.xml");
	
	@RequestMapping(value="/initData", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> initInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String opcode = request.getParameter("opcode");
		String officeId = request.getParameter("officeId");
		
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("opcode", "initData");
		OfficeDao officeDao = (OfficeDao)context.getBean("officeDao");
		Office office = (Office)context.getBean("office");
		if (opcode.equals("initData")) {
			modelMap.put("status", "success");
			List<Clinic> clinics = new ArrayList<Clinic>();
			List<PatientInfo> firTreats = new ArrayList<PatientInfo>();
			List<PatientInfo> twiTreas = new ArrayList<PatientInfo>();
			List<PatientInfo> triTreas = new ArrayList<PatientInfo>();
			office = officeDao.getOffice(officeId);
			/*User user = (User)context.getBean("user");
			user.setOId("id1");
			user.setUserName("user1");
			
			User user1 = (User)context.getBean("user");
			user1.setOId("id2");
			user1.setUserName("user2");
			
			users.add(user);
			users.add(user1);
			modelMap.put("status", "success");
			modelMap.put("officeList", users);*/
			
			
			//Office office = 
		} else {
			modelMap.put("status", "fail");
		};
		modelMap.put("office", office);
		return modelMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/user", method = RequestMethod.POST)
	protected User login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//String username, String password
		String opcode = request.getParameter("opcode");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserDao userDao = (UserDao)context.getBean("userDao");
		User user = (User)context.getBean("user");
		System.out.println(opcode);
		if (opcode.equals("register")) {
			String officeId = request.getParameter("officeId");
			if (userDao.isEmpty(username)) {
				user.setOId(officeId);
				user.setUserName(username);
				user.setPassword(password);
				user.setStatus(Status.success.toString());
				userDao.insert(user);
			} else {
				user.setStatus(Status.user_exited.toString());
			}
		} else if (opcode.equals("0")) {
			System.out.println("login");
			User regiestedUser = userDao.getUserWithPassword(username, password);

			if (regiestedUser != null) {
				regiestedUser.setStatus(Status.success.toString());
			} else {
				regiestedUser = (User)context.getBean("user");
				regiestedUser.setStatus(Status.name_or_password_error.toString());
			}
			return regiestedUser;
		}
		
		return user;
	}
}