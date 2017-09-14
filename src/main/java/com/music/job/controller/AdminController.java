package com.music.job.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.music.job.dao.AdminDAO;
import com.music.job.dao.UserDAO;
import com.music.job.pojo.Admin;
import com.music.job.pojo.Events;
import com.music.job.pojo.Jobs;
import com.music.job.pojo.MessageToAdmin;
import com.music.job.pojo.MusicRecruitor;
import com.music.job.pojo.User;
import com.music.job.otherClasses.EmailDetails;
import com.music.job.otherClasses.SendMail;

@Controller
public class AdminController {
	
	@Autowired
	@Qualifier("userDao")
	UserDAO loginDao;
	
	@Autowired
	@Qualifier("adminDao")
	AdminDAO adminDao;
	
	@RequestMapping(value = "/admin-view-messages.htm", method = RequestMethod.GET)
	public String viewwMessages(Model model) throws Exception {
		ArrayList<MessageToAdmin> message=adminDao.getMesaages();
		model.addAttribute("message",message);
		return "admin_viewMessages";
	}
	
	@RequestMapping(value = "/admin-landing.htm", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request) throws Exception {
		return "admin_login_success";
	}
	
	@RequestMapping(value = "/admin-message-details.htm", method = RequestMethod.GET)
	public String viewDetails(Model model,HttpServletRequest request) throws Exception {
		long id=Long.parseLong(request.getParameter("msgId"));
		MessageToAdmin msg=adminDao.queryMessageById(id);
		MusicRecruitor recruitor=new MusicRecruitor();
		recruitor=msg.getMusicRecruitor();
		model.addAttribute("recruitor",recruitor);
		return "admin_viewDetails";
	}
	
	@RequestMapping(value = "/admin-approve-user.htm", method = RequestMethod.GET)
	public String approve(Model model,HttpServletRequest request) throws Exception {
		long id=Long.parseLong(request.getParameter("recId"));
		User user=loginDao.queryUserById(id);
		user.setStatus("active");
		boolean success=loginDao.saveUser(user);
		
		if(success){
			SendMail emailDetails = new SendMail();
			User user1=(User)request.getSession().getAttribute("user");
			emailDetails.setFromEmail(user1.getEmailId());
			emailDetails.setToEmail(user.getEmailId());
			emailDetails.setSubject("Information for your request to signup on musicjobs.com");
			emailDetails.setMessage("You have successfully signed up on musicjobs.com. Kindly let us know in case of any help required. Happy recruiting. ");
			EmailDetails email=new EmailDetails();
			email.sendMail(emailDetails);
			model.addAttribute("messageType","User saved successfully");
			model.addAttribute("messageDetails","Access has been to the user and email has been sent successfully.");
			return "admin_success";
		}
		model.addAttribute("messageType","User not saved");
		model.addAttribute("messageDetails","Unable to save user due to technical difficulties. Please try again later");
		return "admin_Error";
	}
	
	@RequestMapping(value = "/admin-view-allusers.htm", method = RequestMethod.GET)
	public String viewUsers(Model model,HttpServletRequest request) throws Exception {
		ArrayList<User> users=loginDao.getAllUsers();
		model.addAttribute("user",users);
		return "admin_viewUsers";
	}
	
	@RequestMapping(value = "/admin-disable.htm", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN)
	public @ResponseBody String disableUser(Model model,HttpServletRequest request) throws Exception {
		long id=Long.parseLong(request.getParameter("userId"));
		String userId=request.getParameter("userId");
		User user=loginDao.queryUserById(id);
		user.setStatus("Disabled");
		boolean success=loginDao.saveUser(user);
		if(success){
			SendMail emailDetails = new SendMail();
			User user1=(User)request.getSession().getAttribute("user");
			emailDetails.setFromEmail(user1.getEmailId());
			emailDetails.setToEmail(user.getEmailId());
			emailDetails.setSubject("Temporary termination of usage of musicjobs.com");
			emailDetails.setMessage("You have been temporarily banned from using musicjobs.com due to unusual behaviour.");
			EmailDetails email=new EmailDetails();
			email.sendMail(emailDetails);
			ArrayList<User> users=loginDao.getAllUsers();
			model.addAttribute("user",users);
			return userId;
		}
		model.addAttribute("messageType","User not saved");
		model.addAttribute("messageDetails","Unable to save user due to technical difficulties. Please try again later");
		return "admin_Error";
	}
	
	@RequestMapping(value = "/admin-enable.htm", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN)
	public @ResponseBody String enableUser(Model model,HttpServletRequest request) throws Exception {
		long id=Long.parseLong(request.getParameter("uId"));
		String userId=request.getParameter("uId");
		User user=loginDao.queryUserById(id);
		user.setStatus("active");
		boolean success=loginDao.saveUser(user);
		if(success){
			SendMail emailDetails = new SendMail();
			User user1=(User)request.getSession().getAttribute("user");
			emailDetails.setFromEmail(user1.getEmailId());
			emailDetails.setToEmail(user.getEmailId());
			emailDetails.setSubject("Information for your end of banned term tenure on musicjobs.com");
			emailDetails.setMessage("You banned tenure on musicjobs.com has ended. You can again login and proceed with your functionalities. Kindly let us know in case of any help required. Happy recruiting. ");
			EmailDetails email=new EmailDetails();
			email.sendMail(emailDetails);
			ArrayList<User> users=loginDao.getAllUsers();
			model.addAttribute("user",users);
			return userId;
		}
		model.addAttribute("messageType","User not saved");
		model.addAttribute("messageDetails","Unable to save user due to technical difficulties. Please try again later");
		return "admin_Error";
	}
	
	@RequestMapping(value = "/admin-update-profile.htm", method = RequestMethod.GET)
	public String updateProfile(Model model,HttpServletRequest request) throws Exception {
		User admin = (User) request.getSession().getAttribute("user");
		model.addAttribute("admin", admin);
		return "admin_updateProfile";
	}

	@RequestMapping(value = "/admin-update-details.htm", method = RequestMethod.POST)
	public String updateProfile(Model model, User admin, HttpServletRequest request)
			throws Exception {
		User mus = (User) request.getSession().getAttribute("user");
		admin.setPassword(mus.getPassword());
		
		admin.setStatus(mus.getStatus());
		admin.setRole("admin");
		//musician.setApplications(mus.getApplications());
		long id = mus.getUserId();
//		musician.setUserId(id);

		boolean success = loginDao.saveUser(admin);
		if (success) {
			User user = (User) loginDao.queryUserById(id);
			request.getSession().setAttribute("user", user);
			model.addAttribute("admin", admin);
			return "admin_updateProfile";
		} else {
			model.addAttribute("messageType","Profile not updated");
			model.addAttribute("messageDetails","Unable to update profile details due to technical difficulties. Please try again later");
			return "admin_Error";
		}
	}
	
	@RequestMapping(value = "/admin-post-event.htm", method = RequestMethod.GET)
	public String postEvent(Model model,HttpServletRequest request) throws Exception {
		Events event = new Events();
		model.addAttribute("event", event);
		return "admin_addEvent";
	}
	
	@RequestMapping(value = "/admin-post-event.htm", method = RequestMethod.POST)
	public String postAdminEvent(Model model,HttpServletRequest request, Events event) throws Exception {
		User admin=(User)request.getSession().getAttribute("user");
		event.setUser(admin);
		boolean success=adminDao.saveEvent(event);
		if(success){
			model.addAttribute("messageType","Event posted successfully");
			model.addAttribute("messageDetails","Event posted successfully. You can view the event and event details in your events section");
			return "admin_success";
		}
		model.addAttribute("messageType","Event not posted");
		model.addAttribute("messageDetails","Unable to post event due to technical difficulties. Please try again later");
		return "admin_Error";
	}
	
	@RequestMapping(value = "/admin-view-event.htm", method = RequestMethod.GET)
	public String viewEvent(Model model,HttpServletRequest request) throws Exception {
		ArrayList<Events> event=adminDao.getEvents();
		model.addAttribute("eventList",event);
		return "admin_viewEvent";
	}
	
	@RequestMapping(value = "/admin-view-participants.htm", method = RequestMethod.GET)
	public String viewApplicants(Model model,HttpServletRequest request) throws Exception {
		int eventId=Integer.parseInt(request.getParameter("eventId"));
		Events event= adminDao.queryEventById(eventId);
		model.addAttribute("participants",event.getMusicianList());
		return "admin_ViewParticipants";
	}
	
}
