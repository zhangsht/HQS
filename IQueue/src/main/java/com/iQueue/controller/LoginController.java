package com.iQueue.controller;
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iQueue.dao.UserDao;
import com.iQueue.model.Queue;
import com.iQueue.model.Status;
import com.iQueue.model.User;
 
@Controller
public class LoginController {
	private ApplicationContext context = 
			new ClassPathXmlApplicationContext("applicationContext.xml");
	
	public static void main(String[] args) {
		Date date = new Date();

		System.out.println(date);
	}
	
	@RequestMapping(value="/initInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> initInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String opcode = request.getParameter("opcode");
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("opcode", "init");
		if (opcode.equals("init")) {
			modelMap.put("status", "success");
			
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
		} else {
			modelMap.put("status", "fail");
		};
		
		return modelMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/user", method = RequestMethod.POST)
	protected User login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String opcode = request.getParameter("opcode");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserDao userDao = new UserDao();
		User user = new User();
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
				regiestedUser = new User();
				regiestedUser.setStatus(Status.name_or_password_error.toString());
			}
			return regiestedUser;
		}
		
		return user;
	}
}