package com.iQueue.controller;
 
import java.util.ArrayList;
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
import com.iQueue.model.Status;
import com.iQueue.model.User;
import com.iQueue.model.Office;
 
@Controller
public class LoginController {
	private ApplicationContext context = 
			new ClassPathXmlApplicationContext("applicationContext.xml");
	
	/*public static void main(String[] args) {
		ApplicationContext context = 
	             new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDao userDao = (UserDao)context.getBean("userDao");
		User user = (User)context.getBean("user");
		user.setId("office1");
		user.setUserName("user1");
		user.setPassword("password1");
		user.setStatus("success");
		userDao.insert(user);
	}*/

	
	@RequestMapping(value="/initInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> initInfo(String opcode) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("opcode", "init");
		if (opcode.equals("init")) {
			List<User> users = new ArrayList<User>();
			User user = (User)context.getBean("user");
			user.setOId("id1");
			user.setUserName("user1");
			
			User user1 = (User)context.getBean("user");
			user1.setOId("id2");
			user1.setUserName("user2");
			
			users.add(user);
			users.add(user1);
			modelMap.put("status", "success");
			modelMap.put("officeList", users);
		} else {
			modelMap.put("status", "fail");
		};
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
			if (userDao.isExit(username)) {
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
	
	@RequestMapping(value="/test", method = RequestMethod.GET)
	public String test() {
		UserDao userDao = (UserDao)context.getBean("userDao");
//		User user = (User)context.getBean("user");
//		userDao.insert(user);
		User user = userDao.getUser("123");
		System.out.println(user.getUserName());
		return "test";
	}
}