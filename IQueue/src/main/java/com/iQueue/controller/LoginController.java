package com.iQueue.controller;
 
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iQueue.dao.ClinicDao;
import com.iQueue.dao.UserDao;
import com.iQueue.model.Clinic;
import com.iQueue.model.Queue;
import com.iQueue.model.Status;
import com.iQueue.model.User;
 
@Controller
public class LoginController {
	private ApplicationContext context = 
			new ClassPathXmlApplicationContext("applicationContext.xml");
	
	public static void main(String[] args) {
		Date date = new Date();
		String ss = new String("" + date.getTime());

		System.out.println(ss);
	}
	
	@RequestMapping(value="/initInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> initInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String opcode = request.getParameter("opcode");
		//String officeId = request.getParameter("officeId");
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("opcode", "init");
		//OfficeDao officeDao = (OfficeDao)context.getBean("officeDao");
		//Office office = (Office)context.getBean("office");
		if (opcode.equals("init")) {
			modelMap.put("status", "success");
			/*List<Clinic> clinics = new ArrayList<Clinic>();
			
			List<PatientInfo> firTreats = new ArrayList<PatientInfo>();
			List<PatientInfo> twiTreas = new ArrayList<PatientInfo>();
			List<PatientInfo> triTreas = new ArrayList<PatientInfo>();*/
			
			List<Queue> nameList = new ArrayList<Queue>();
			
			for (int i = 0; i < 2; i++) {
				Date date = new Date();
				long s = date.getTime();
				String ss = new String("" + s);
				Queue queue = new Queue("", "patient " + i, ss, "");
				nameList.add(queue);
				Thread.sleep(1000);
			}
			
			Collections.reverse(nameList);
			modelMap.put("first_queue", nameList);
			//office = officeDao.getOffice(officeId);
		} else {
			modelMap.put("status", "fail");
		};
		//modelMap.put("office", office);
		
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
		} else if (opcode.equals("login")) {
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