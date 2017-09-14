package com.music.job.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.music.job.dao.UserDAO;
import com.music.job.exception.UserException;
import com.music.job.pojo.User;

@Controller
public class LoginController {
	@Autowired
	@Qualifier("userDao")
	UserDAO loginDao;

	@Autowired
	ServletContext servletContext;

	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		return "base_index";
	}
	
	@RequestMapping(value = "/login.htm", method = RequestMethod.GET)
	public String home(HttpServletRequest request,Model model) {
		
		request.getSession().removeAttribute("user");
		request.getSession().invalidate();
		return "base_index";
	}
	
	@RequestMapping(value = "/home.htm", method = RequestMethod.GET)
	public String homepage(HttpServletRequest request,Model model) {

		return "base_index";
	}
	
	@RequestMapping(value = "/home.htm", method = RequestMethod.POST)
	public String checkLogin(HttpServletRequest request,Model model) throws UserException {

		HttpSession session = (HttpSession) request.getSession();
			System.out.print("loginUser");

			User l = loginDao.get(request.getParameter("username"), request.getParameter("password"));
			String role = request.getParameter("role");
			if (l == null) {
				model.addAttribute("messageType","Username/Password error");
				model.addAttribute("messageDetails","Unable to login since username or password is incorrect. Please enter correct credentials to proceed");
				return "base_error";
			}
			if (l.getRole().equalsIgnoreCase(role)) {
				session.setAttribute("user", l);
				if (l.getRole().equals("admin")) {
					request.getSession().setAttribute("username", l.getFirstName());
					request.getSession().setAttribute("user", l);
					return "admin_login_success";
				} else if (l.getRole().equals("musicRecruitor") && l.getStatus().equals("active")) {
					request.getSession().setAttribute("username", l.getFirstName());
					request.getSession().setAttribute("user", l);
					return "recruitor_login_success";
				} else if (l.getRole().equals("musician")) {
					request.getSession().setAttribute("username", l.getFirstName());
					request.getSession().setAttribute("user", l);
					return "musician_login_success";
				}
			}
			model.addAttribute("messageType","Username/Password error");
			model.addAttribute("messageDetails","Username/password you have provided does not belong to the selected role. Please enter correct credentials to proceed");
			return "base_error";
	}

	@RequestMapping(value = "/signup.htm", method = RequestMethod.POST)

	public @ResponseBody boolean submit(User user) {

		return true;
	}

	@RequestMapping(value = "/forgotPassword.htm", method = RequestMethod.GET)
	public String forgotPassword() {
		return "forgotPassword";
	}

	@RequestMapping(value = "/forgotPassword.htm", method = RequestMethod.POST)
	public String postForgotPassword(Model model,HttpServletRequest request) throws UserException {
		String username = request.getParameter("username");
		request.getSession().setAttribute("username", username);
		User user = loginDao.getByUsername(username);
		if(user==null){
			model.addAttribute("messageType","Invalid username");
			model.addAttribute("messageDetails","Please enter a valid username");
			return "signup_error";
		}
		return "forgotPasswordDetails";
	}

	@RequestMapping(value = "/forgotPasswordDetails.htm", method = RequestMethod.POST)
	public String forgotPasswordDetails(Model model,HttpServletRequest request) throws Exception {
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");
		String username = (String) request.getSession().getAttribute("username");
		User user = loginDao.getByUsername(username);
		if (oldPassword.equals(user.getPassword()) && newPassword.equals(confirmPassword)) {
			user.setPassword(newPassword);
			boolean success = loginDao.saveUser(user);
			if (success){
				model.addAttribute("messageType","Password changed successfully");
				model.addAttribute("messageDetails","Password has been updated successfully");
				return "base_success";
			}
			else
				model.addAttribute("messageType","Password error");
			model.addAttribute("messageDetails","Unable to update the password. Please try again later");
			return "signup_error";
		} else {
			model.addAttribute("messageType","Password error");
			model.addAttribute("messageDetails","Unable to update the password. Please try again later");
			return "signup_error";
		}

	}

}
