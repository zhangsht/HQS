package com.iQueue.controller;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iQueue.dao.UserDao;
import com.iQueue.model.Status;
import com.iQueue.model.User;
 
@Controller
public class LoginController {
	@ResponseBody
	@RequestMapping(value="/user", method = RequestMethod.POST)
	protected User login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = new User();
		try {
			String opcode = request.getParameter("opcode");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			UserDao userDao = new UserDao();
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
				User regiestedUser = userDao.getUserWithPassword(username, password);

				if (regiestedUser != null) {
					regiestedUser.setStatus(Status.success.toString());
				} else {
					regiestedUser = new User();
					regiestedUser.setStatus(Status.name_or_password_error.toString());
				}
				return regiestedUser;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}