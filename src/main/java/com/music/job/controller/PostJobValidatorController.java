package com.music.job.controller;

import javax.servlet.http.HttpServletRequest;

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
import com.music.job.pojo.Jobs;
import com.music.job.pojo.MusicRecruitor;
import com.music.job.validator.JobValidator;

@Controller
public class PostJobValidatorController {
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
	
	@Autowired
	@Qualifier("jobValidator")
	JobValidator jobValidator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(jobValidator);
	}
	
	@RequestMapping(value="/musicrecruitor-postjob.htm", method=RequestMethod.GET)
	public String postJob(Model model){
		Jobs jobs=new Jobs();
		model.addAttribute("jobs",jobs);
		return "recruitor_postjob";
	}
	
	@RequestMapping(value="/musicrecruitor-postjob.htm", method=RequestMethod.POST)
	public String postJobSubmit(@ModelAttribute("jobs")Jobs jobs,Model model,HttpServletRequest request , BindingResult result){
		jobValidator.validate(jobs, result);
		if(result.hasErrors()){
			model.addAttribute("jobs",jobs);
			return "recruitor_postjob";
		}
		
		MusicRecruitor mr=(MusicRecruitor)request.getSession().getAttribute("user");
		jobs.setPostedBy(mr);
		boolean success=recruitorDao.saveJobPosting(jobs);
		if(success){
			model.addAttribute("messageType", "Job posted successfully");
			model.addAttribute("messageDetails","Job has been posted successfully.");
			return "recruitor_success";
		}
		else {
			model.addAttribute("messageType", "Unable to post the job");
			model.addAttribute("messageDetails","Unable to post the job due to technical difficulties. Please try again later");
			return "recruitor_Error";
		}
	}
	
}
