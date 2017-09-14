package com.music.job.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.music.job.dao.MessageDAO;
import com.music.job.dao.MusicRecruitorDAO;
import com.music.job.dao.MusicianDAO;
import com.music.job.dao.UserDAO;
import com.music.job.otherClasses.EmailDetails;
import com.music.job.otherClasses.SendMail;
import com.music.job.pojo.Applications;
import com.music.job.pojo.Jobs;
import com.music.job.pojo.MessageToAdmin;
import com.music.job.pojo.MusicRecruitor;
import com.music.job.pojo.Musician;
import com.music.job.pojo.User;
import com.music.job.validator.JobValidator;


@Controller
public class MusicRecruitorController {
	
	@Autowired
	@Qualifier("userDao")
	UserDAO loginDao;
	
	@Autowired
	@Qualifier("recruitorDao")
	MusicRecruitorDAO recruitorDao;
	
	@Autowired
	@Qualifier("messageDao")
	MessageDAO messageDao;
	
	@Autowired
	@Qualifier("musicianDao")
	MusicianDAO musicianDao;
	

	
	@RequestMapping(value = "/musicrecruitor-signup.htm", method = RequestMethod.GET)
	public String Signup(Model model) {
		MusicRecruitor musicRecruitor=new MusicRecruitor();
		model.addAttribute(musicRecruitor);
		return "recruitor_signup";
	}
	
	@RequestMapping(value = "/musicrecruitor-landing.htm", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request) throws Exception {
		return "recruitor_login_success";
	}
	
	@RequestMapping(value="/musicrecruitor-signup.htm", method=RequestMethod.POST)
	public String postSignup(Model model, HttpServletRequest request, MusicRecruitor musicRecruitor) throws Exception{
		musicRecruitor.setRole("musicRecruitor");
		musicRecruitor.setStatus("Disabled");
		String username=musicRecruitor.getUsername();
		String emailId=musicRecruitor.getEmailId();
		boolean checkEmail=musicianDao.checkEmail(emailId);
		boolean checkUsername=musicianDao.checkUsername(username);
		if(!checkEmail){
			model.addAttribute("messageType", "Unable to Signup");
			model.addAttribute("messageDetails","Unable to signup since email id already exists. Please try again with different email id.");
			return "signup_error";
		}
		else if(!checkUsername){
			model.addAttribute("messageType", "Unable to Signup");
			model.addAttribute("messageDetails","Unable to signup since username already exists. Please try again with different username.");
			return "signup_error";
		}
		boolean success=loginDao.saveUser(musicRecruitor);
		MessageToAdmin msg=new MessageToAdmin();
		msg.setMusicRecruitor(musicRecruitor);
		msg.setMessage("Hey Admin, Music Recruitor "+musicRecruitor.getFirstName()+" wants to join music job portal. Kindly approve the request.");
		boolean success1=messageDao.saveMessage(msg);
		if(success && success1){
			model.addAttribute("messageType", "Signup successfully");
			model.addAttribute("messageDetails","Your request for signup has been raised to admin. You will receive an email once it is approved.");
			return "recruitor_success";
		}
		else {
			model.addAttribute("messageType", "Unable to Signup");
			model.addAttribute("messageDetails","Unable to signup due to technical difficulties. Please try again later.");
			return "base_error";
		}
	}
	
	@RequestMapping(value="/musicrecruitor-getpostedjobs.htm", method=RequestMethod.GET)
	public String getJobs(Model model,HttpSession session) throws Exception{
		MusicRecruitor mr=(MusicRecruitor)session.getAttribute("user");
		ArrayList<Jobs> jobs=recruitorDao.getMyJobs(mr);
		model.addAttribute("jobs",jobs);
		return "recruitor_getJobs";
	}
	
	
	@RequestMapping(value = "/musicrecruitor-viewjobapplications.htm", method = RequestMethod.GET)
	public String viewTotalApplications(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		int jobId=Integer.parseInt(request.getParameter("jobId"));
		Jobs job= recruitorDao.getJob(jobId);
		model.addAttribute("applications",job.getApplications());
		return "recruitor_ViewApplications";
	}
	
	@RequestMapping(value = "/downloadRec", method = RequestMethod.GET)
	public void downloadOldResume(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		long id=Long.parseLong(request.getParameter("id"));
		Musician musician= (Musician) loginDao.queryUserById(id);
		File f= new File(musician.getResume());
		FileInputStream fis= new FileInputStream(f);
		response.setHeader("Expires", "0");
        response.setHeader("Cache-Control","must-revalidate, post-check=0,precheck=0");
        response.setHeader("Pragma", "public");
        response.setContentType("application/pdf");
        response.setContentLength((int) f.length());
        OutputStream os = response.getOutputStream();
        IOUtils.copy(fis, os);
        os.flush();
        os.close(); 
	}
	
	@RequestMapping(value = "/musicrecruitor-sendemail.htm", method = RequestMethod.GET)
	public String email(Model model,HttpServletRequest request) throws Exception
	{
		long musicianId=Long.parseLong(request.getParameter("userId"));
		Musician musician=(Musician) loginDao.queryUserById(musicianId);
		String status=request.getParameter("status");
		int jobId=Integer.parseInt(request.getParameter("jobId"));
		Applications application=recruitorDao.get(jobId, musicianId);
		application.setJobStatus(status);
		musicianDao.saveApplication(application);
		Jobs job=(Jobs)recruitorDao.getJob(jobId);
		MusicRecruitor recruitor=(MusicRecruitor)request.getSession().getAttribute("user");
		request.setAttribute("jobId", jobId);
		SendMail emailDetails= new SendMail();
		emailDetails.setFromEmail(recruitor.getEmailId());
		emailDetails.setToEmail(musician.getEmailId());
		emailDetails.setSubject("Information regarding application for "+job.getJobTitle()+" position at "+job.getCompanyName());
		model.addAttribute("emailDetails",emailDetails);
		return "recruitor_sendEmail";
	}
	
	@RequestMapping(value = "/musicrecruitor-sendemail.htm", method = RequestMethod.POST)
	public String sendEmailSubmit(Model model,SendMail emailDetails,HttpServletRequest request) throws Exception
	{
		EmailDetails email=new EmailDetails();
		boolean success=email.sendMail(emailDetails);
		if(success)
			{int jobId=Integer.parseInt(request.getParameter("jobId"));
		Jobs job= recruitorDao.getJob(jobId);
		model.addAttribute("applications",job.getApplications());
		return "recruitor_ViewApplications";}
		else{
			model.addAttribute("messageType", "Unable to send the mail");
			model.addAttribute("messageDetails","Unable to send the mail due to technical difficulties. Please try again later");
			return "recruitor_Error";
		}
			
	}
	
	@RequestMapping(value = "/musicrecruitor-updatedetails.htm", method = RequestMethod.GET)
	public String updateProfile(Model model,HttpServletRequest request) throws Exception {
		MusicRecruitor musician = (MusicRecruitor) request.getSession().getAttribute("user");
		model.addAttribute("musician", musician);
		return "recruitor_updateProfile";
	}

	@RequestMapping(value = "/musicrecruitor-updatedetails.htm", method = RequestMethod.POST)
	public String updateProfile(Model model, MusicRecruitor musician, HttpServletRequest request)
			throws Exception {
		MusicRecruitor mus = (MusicRecruitor) request.getSession().getAttribute("user");
		musician.setPassword(mus.getPassword());
		musician.setStatus(mus.getStatus());
		musician.setRole("musicRecruitor");
		//musician.setApplications(mus.getApplications());
		long id = mus.getUserId();
//		musician.setUserId(id);

		boolean success = loginDao.saveUser(musician);
		if (success) {
			User user = (User) loginDao.queryUserById(id);
			request.getSession().setAttribute("user", user);
			model.addAttribute("musician", musician);
			return "recruitor_updateProfile";
		} else {
			model.addAttribute("messageType", "Unable to update the profile");
			model.addAttribute("messageDetails","Unable to update the profile due to technical difficulties. Please try again later");
			return "recruitor_Error";
		}
	}
	
	@RequestMapping(value = "/musicrecruitor-search-musician.htm", method = RequestMethod.GET)
	public String connectRecruitor(Model model,HttpServletRequest request) throws Exception {
		ArrayList<Musician> mr=recruitorDao.getMusician(); 
		
		model.addAttribute("mr",mr);
		return "recruitor_ViewMusician";
	}
	
	@RequestMapping(value = "/musicrecruitor-viewmusiciandetails.htm", method = RequestMethod.GET)
	public String checkMusician(Model model,HttpServletRequest request) throws Exception {
		long recId=Long.parseLong(request.getParameter("recId"));
		MusicRecruitor mr = (MusicRecruitor) request.getSession().getAttribute("user");
		long id = mr.getUserId();
		
		mr = (MusicRecruitor) loginDao.queryUserById(id); 
		Musician mus=(Musician)loginDao.queryUserById(recId);
		if(mr.getmConnections().contains(mus) || mus.getMrConnections().contains(mr))
			model.addAttribute("yes","no");
		else
			model.addAttribute("yes","yes");
		model.addAttribute("mr",mus);
		return "recruitor_ViewMusicianDetails";
	}
	
	@RequestMapping(value = "/musicrecruitor-connectmusician.htm", method = RequestMethod.GET)
	public String connectToMusician(Model model,HttpServletRequest request) throws Exception {
		long recId=Long.parseLong(request.getParameter("recId"));
		MusicRecruitor mr = (MusicRecruitor) request.getSession().getAttribute("user");
		Musician musician=(Musician)loginDao.queryUserById(recId);
		long id = mr.getUserId();
		
		mr = (MusicRecruitor) loginDao.queryUserById(id);
		mr.getmConnections().add(musician);
		boolean success=recruitorDao.addMusicianRec(mr);
		if(success){
			model.addAttribute("messageType", "Successfully connected");
			model.addAttribute("messageDetails", "Successfully connected to the musician");
			return "recruitor_success";
		}
		else{
			model.addAttribute("messageType", "Cannot connect");
			model.addAttribute("messageDetails",
					"Unable to connect to musician due to technical difficulties. Please try again later");
			return "recruitor_Error";
		}
	}
	
	@RequestMapping(value = "/musicrecruitor-emailmusician.htm", method = RequestMethod.GET)
	public String emailMusician(Model model,HttpServletRequest request) throws Exception
	{
		long recId=Long.parseLong(request.getParameter("recId"));
		MusicRecruitor mr = (MusicRecruitor) request.getSession().getAttribute("user");
		Musician musician=(Musician)loginDao.queryUserById(recId);
		long id = mr.getUserId();
		
		mr = (MusicRecruitor) loginDao.queryUserById(id);
		
		SendMail emailDetails= new SendMail();
		emailDetails.setFromEmail(mr.getEmailId());
		emailDetails.setToEmail(musician.getEmailId());
		model.addAttribute("emailDetails",emailDetails);
		return "recruitor_sendMusicianEmail";
	}
//	
	@RequestMapping(value = "/musicrecruitor-emailmusician.htm", method = RequestMethod.POST)
	public String emailMusicianSubmit(Model model,SendMail emailDetails,HttpServletRequest request) throws Exception
	{
		EmailDetails email=new EmailDetails();
		boolean success=email.sendMail(emailDetails);
		if (success) {
			model.addAttribute("messageType","Email send successfully");
			model.addAttribute("messageDetails","Email send successfully to the musician");
			return "musician_success";
		} else {
			model.addAttribute("messageType","Email not send");
			model.addAttribute("messageDetails","Unable to send email due to technical difficulties. Please try again later");
			return "musician_Error";
		}
	}
//	
	@RequestMapping(value = "/musicrecruitor-myconnections.htm", method = RequestMethod.GET)
	public String viewConnections(Model model,HttpServletRequest request) throws Exception {
		MusicRecruitor mr = (MusicRecruitor) request.getSession().getAttribute("user");
		model.addAttribute("connections",mr.getmConnections());
		model.addAttribute("username",mr.getFirstName());
		return "musicRecruitor_MyConnections";
	}
}
