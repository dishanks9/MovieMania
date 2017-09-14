package com.music.job.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import com.music.job.dao.MusicianDAO;
import com.music.job.dao.UserDAO;
import com.music.job.pojo.Applications;
import com.music.job.pojo.Events;
import com.music.job.pojo.Jobs;
import com.music.job.pojo.MusicRecruitor;
import com.music.job.pojo.Musician;
import com.music.job.pojo.User;
import com.music.job.otherClasses.EmailDetails;
import com.music.job.otherClasses.ExcelView;
import com.music.job.otherClasses.SendMail;

@Controller
public class MusicianController {
	@Autowired
	@Qualifier("userDao")
	UserDAO loginDao;

	@Autowired
	@Qualifier("musicianDao")
	MusicianDAO musicianDao;

	@RequestMapping(value = "/musician-signup.htm", method = RequestMethod.GET)
	public String Signup(Model model) {
		Musician musician = new Musician();
		model.addAttribute(musician);
		return "musician_signup";
	}
	
	@RequestMapping(value = "/musician-landing.htm", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request) throws Exception {
		return "musician_login_success";
	}

	@RequestMapping(value = "/musician-signup.htm", method = RequestMethod.POST)
	public String postSignup(Model model, HttpServletRequest request, Musician musician) throws Exception {
		musician.setRole("musician");
		musician.setStatus("active");
		String username=musician.getUsername();
		String emailId=musician.getEmailId();
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
		boolean success = loginDao.saveUser(musician);
		if (success) {
			request.getSession().setAttribute("username", musician.getFirstName());
			request.getSession().setAttribute("user", musician);
			model.addAttribute("messageType", "Successful signup");
			model.addAttribute("messageDetails", "Congrats on sign up with musicjobs.com. Happy job hunt.");
			return "musician_success";
		} else {
			model.addAttribute("messageType", "Unable to Signup");
			model.addAttribute("messageDetails","Unable to signup due to technical difficulties. Please try again later.");
			return "musician_Error";
		}
	}

	@RequestMapping(value = "/musician-search-jobs.htm", method = RequestMethod.GET)
	public String searchJobs(Model model, HttpServletRequest request, Musician musician) throws Exception {

		ArrayList<Jobs> jobs = musicianDao.listOfJobs();
		model.addAttribute("jobs", jobs);
		Musician musician1 = (Musician) request.getSession().getAttribute("user");
		String resume=musician1.getResume();
		System.out.println(resume);
		if(!resume.equals("")){
			model.addAttribute("resume", "yes");
		}
		else
		model.addAttribute("resume", "no");
		return "musician_searchjob";
	}

	@RequestMapping(value = "/musician-applyToJob.htm", method = RequestMethod.POST)
	public String applyToJob(Model model, HttpServletRequest request, @RequestParam("file") CommonsMultipartFile resume)
			throws Exception {
		HttpSession session = request.getSession();
		Musician musician = (Musician) session.getAttribute("user");
		long id = musician.getUserId();
		String check=request.getParameter("res");
		boolean success = false;
		if(check.equals("new")){
			if(resume==null){
				model.addAttribute("messageType", "Please upload resume and submit");
				model.addAttribute("messageDetails","Please upload the resume");
				return "musician_Error";
			}
		File localFile = null;
		try {
			System.out.println(request.getSession().getServletContext().getRealPath("/webapps/"));
			String rootPath = System.getProperty("catalina.home");
			File path = new File(rootPath + File.separator + "MusicCareers" + File.separator + "Musician"
					+ File.separator + "Resumes" + File.separator + musician.getUserId() + File.separator);
			if (!path.exists())
				path.mkdirs();
			localFile = new File(path.getAbsoluteFile(), String.valueOf(musician.getUserId()) + ".pdf");
			resume.transferTo(localFile);
			success = true;
			musician = (Musician) loginDao.queryUserById(id);
			musician.setResume(localFile.getPath());
		} catch (Exception ex) {
			success = false;
		}}
		else{
			success=true;
		}
		if (success) {
			System.out.println("abc");
			musician = (Musician) loginDao.queryUserById(id);
			loginDao.saveUser(musician);
			request.getSession().setAttribute("musician", musician);
			int jobId = Integer.parseInt(request.getParameter("id"));
			Applications application = new Applications();
			Jobs job = musicianDao.getJob(jobId);
			application.setUser(musician);
			application.setDateApplied(new Date().toString());
			application.setJob(job);
			application.setJobStatus("Application submitted");
			boolean success1 = musicianDao.saveApplication(application);
			if (success1) {
				SendMail emailDetails = new SendMail();
				emailDetails.setFromEmail(job.getPostedBy().getEmailId());
				emailDetails.setToEmail(musician.getEmailId());
				emailDetails.setSubject(
						"Thank you for applying for " + job.getJobTitle() + " position at " + job.getCompanyName());
				emailDetails.setMessage("You have successfully applied for " + job.getJobTitle() + " position at "
						+ job.getCompanyName());
				EmailDetails email = new EmailDetails();
				email.sendMail(emailDetails);
				model.addAttribute("messageType", "Successful job application");
				model.addAttribute("messageDetails", "You have successfully applied to job.");
				return "musician_success";
			} else {
				model.addAttribute("messageType", "Unable to forward your application");
				model.addAttribute("messageDetails",
						"Unable to forward your application due to technical difficulties. Please try again later");
				return "musician_Error";
			}
		} else {
			model.addAttribute("messageType", "Unable to upload resume to the database");
			model.addAttribute("messageDetails",
					"Unable to upload resume due to technical difficulties. Please try again later");
			return "musician_Error";
		}
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void downloadOldResume(Model model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Musician musician = (Musician) request.getSession().getAttribute("user");
		File file = new File(musician.getResume());
		FileInputStream stream = new FileInputStream(file);
		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control", "must-revalidate, post-check=0,precheck=0");
		response.setHeader("Pragma", "public");
		response.setContentType("application/pdf");
		response.setContentLength((int) file.length());
		OutputStream os = response.getOutputStream();
		IOUtils.copy(stream, os);
		os.flush();
		os.close();
	}

	@RequestMapping(value = "/musician-getMyApplications.htm", method = RequestMethod.GET)
	public String getJobs(Model model, HttpSession session) throws Exception {
		Musician musician = (Musician) session.getAttribute("user");
		ArrayList<Applications> applications = musicianDao.getMyApplications(musician);
		model.addAttribute("applications", applications);
		return "musician_getJobs";
	}

	@RequestMapping(value = "/musician-update-profile.htm", method = RequestMethod.GET)
	public String updateProfile(Model model, HttpServletRequest request) throws Exception {
		Musician musician = (Musician) request.getSession().getAttribute("user");
		model.addAttribute("musician", musician);
		return "musician_updateProfile";
	}

	@RequestMapping(value = "/musician-updatedetails.htm", method = RequestMethod.POST)
	public String updateProfile(Model model, @ModelAttribute("musician") Musician musician, HttpServletRequest request)
			throws Exception {
		Musician mus = (Musician) request.getSession().getAttribute("user");
		musician.setPassword(mus.getPassword());
		musician.setResume(mus.getResume());
		musician.setStatus(mus.getStatus());
		musician.setRole("musician");
		// musician.setApplications(mus.getApplications());
		long id = mus.getUserId();
		// musician.setUserId(id);
		User u = (User) musician;
		boolean success = loginDao.saveUser(u);
		if (success) {
			User user = (User) loginDao.queryUserById(id);
			request.getSession().setAttribute("user", user);
			model.addAttribute("musician", musician);
			return "musician_updateProfile";
		} else {
			model.addAttribute("messageType", "Profile not updated");
			model.addAttribute("messageDetails",
					"Unable to update profile details due to technical difficulties. Please try again later");
			return "musician_Error";
		}
	}

	@RequestMapping(value = "/musician-view-events.htm", method = RequestMethod.GET)
	public String viewEvents(Model model, HttpSession session) throws Exception {
		ArrayList<Events> event = musicianDao.listOfEvents();
		model.addAttribute("events", event);
		return "musician_viewEvents";
	}

	@RequestMapping(value = "/musician-register-event.htm", method = RequestMethod.GET)
	public String registerForEvent(Model model, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		long id = user.getUserId();
		user = (Musician) loginDao.queryUserById(id);
		int eventId = Integer.parseInt(request.getParameter("eventId"));
		boolean checkEvent = musicianDao.checkEvent(id, eventId);
		if (!checkEvent) {
			model.addAttribute("messageType", "Duplicate registeration");
			model.addAttribute("messageDetails", "You have already registered for this event");
			return "musician_Error";
		}
		Events event = musicianDao.getEvent(eventId);
		event.getMusicianList().add(user);
		boolean success = musicianDao.addEvent(event);
		if (success) {
			SendMail emailDetails = new SendMail();
			emailDetails.setFromEmail(event.getUser().getEmailId());
			emailDetails.setToEmail(user.getEmailId());
			emailDetails.setSubject("a");
			emailDetails.setMessage("abc");
			EmailDetails email = new EmailDetails();
			email.sendMail(emailDetails);
			model.addAttribute("messageType", "Registeration successful");
			model.addAttribute("messageDetails", "Successfully registered for the event");
			return "musician_success";
		} else
			model.addAttribute("messageType", "Not registered for event");
		model.addAttribute("messageDetails",
				"Unable to register for event due to technical difficulties. Please try again later");
		return "musician_Error";
	}

	@RequestMapping(value = "/musician-myevents.htm", method = RequestMethod.GET)
	public String viewEvents(Model model, HttpServletRequest request) throws Exception {
		Musician musician = (Musician) request.getSession().getAttribute("user");
		model.addAttribute("events", musician.getEvent());
		return "musician_MyEvents";
	}

	@RequestMapping(value = "/musician-viewjobdetails.htm", method = RequestMethod.GET)
	public String viewJobDetails(Model model, HttpServletRequest request) throws Exception {
		int jobId = Integer.parseInt(request.getParameter("jobId"));
		Jobs job = musicianDao.getJob(jobId);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		boolean check = musicianDao.getAppliedJob(user, job);
		if (check) {
			model.addAttribute("applied", "applied");
		} else {
			model.addAttribute("applied", "napplied");
		}
		model.addAttribute("job", job);
		return "musician_ViewJobDetails";
	}

	@RequestMapping(value = "/musician-search-recruitor.htm", method = RequestMethod.GET)
	public String connectRecruitor(Model model, HttpServletRequest request) throws Exception {
		ArrayList<MusicRecruitor> mr = musicianDao.getRecruitor();

		model.addAttribute("mr", mr);
		return "musician_ViewRecruitor";
	}

	@RequestMapping(value = "/musician-viewrecruitordetails.htm", method = RequestMethod.GET)
	public String checkRecruitor(Model model, HttpServletRequest request) throws Exception {
		long recId = Long.parseLong(request.getParameter("recId"));
		Musician musician = (Musician) request.getSession().getAttribute("user");
		long id = musician.getUserId();
		System.out.println("abc");
		musician = (Musician) loginDao.queryUserById(id);
		MusicRecruitor mr = (MusicRecruitor) loginDao.queryUserById(recId);
		if (musician.getMrConnections().contains(mr))
			model.addAttribute("yes", "no");
		else
			model.addAttribute("yes", "yes");
		model.addAttribute("mr", mr);
		return "musician_ViewRecruitorDetails";
	}

	@RequestMapping(value = "/musician-connect-recruitor.htm", method = RequestMethod.GET)
	public String connectToRecruitor(Model model, HttpServletRequest request) throws Exception {
		long recId = Long.parseLong(request.getParameter("recId"));
		Musician musician = (Musician) request.getSession().getAttribute("user");
		MusicRecruitor mr = (MusicRecruitor) loginDao.queryUserById(recId);
		long id = musician.getUserId();
		System.out.println("abc");
		musician = (Musician) loginDao.queryUserById(id);
		musician.getMrConnections().add(mr);
		boolean success = musicianDao.addMusician(musician);
		if (success) {
			model.addAttribute("messageType", "Successfully connected");
			model.addAttribute("messageDetails", "Successfully connected to the recruitor");
			return "musician_success";
		} else {
			model.addAttribute("messageType", "Cannot connect");
			model.addAttribute("messageDetails",
					"Unable to connect to recruitor due to technical difficulties. Please try again later");
			return "musician_Error";
		}
	}

	@RequestMapping(value = "/musician-emailrecruitor.htm", method = RequestMethod.GET)
	public String email(Model model, HttpServletRequest request) throws Exception {
		long recId = Long.parseLong(request.getParameter("recId"));
		Musician musician = (Musician) request.getSession().getAttribute("user");
		MusicRecruitor recruitor = (MusicRecruitor) loginDao.queryUserById(recId);
		long id = musician.getUserId();
		System.out.println("abc");
		musician = (Musician) loginDao.queryUserById(id);

		SendMail emailDetails = new SendMail();
		emailDetails.setFromEmail(musician.getEmailId());
		emailDetails.setToEmail(recruitor.getEmailId());
		model.addAttribute("emailDetails", emailDetails);
		return "musician_sendEmail";
	}

	@RequestMapping(value = "/musician-emailrecruitor.htm", method = RequestMethod.POST)
	public String sendEmailSubmit(Model model, SendMail emailDetails, HttpServletRequest request) throws Exception {
		EmailDetails email = new EmailDetails();
		boolean success = email.sendMail(emailDetails);
		if (success) {
			model.addAttribute("messageType","Email send successfully");
			model.addAttribute("messageDetails","Email send successfully to the recruitor");
			return "musician_success";
		} else {
			model.addAttribute("messageType","Email not send");
			model.addAttribute("messageDetails","Unable to send email due to technical difficulties. Please try again later");
			return "musician_Error";
		}
	}

	@RequestMapping(value = "/musician-myconnections.htm", method = RequestMethod.GET)
	public String viewConnections(Model model, HttpServletRequest request) throws Exception {
		Musician musician = (Musician) request.getSession().getAttribute("user");
		model.addAttribute("connections", musician.getMrConnections());
		model.addAttribute("username", musician.getFirstName());
		return "musician_MyConnections";
	}
	
	@RequestMapping(value = "/report.xls", method = RequestMethod.GET)
	public ModelAndView excelReport(HttpServletRequest request) {
		Set<Applications> user=new HashSet<Applications>();
		Musician musician = (Musician) request.getSession().getAttribute("user");
		user=musician.getApplications();
		View view = new ExcelView();
		
		return new ModelAndView(view,"list",user);
	}
	
}
