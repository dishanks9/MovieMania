package com.music.job.otherClasses;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.music.job.pojo.User;

public class InterceptorClass extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			   HttpServletResponse response, Object handler) throws Exception{
		
		String url=request.getRequestURI();
		if(!url.endsWith("jobportal.htm") && !url.endsWith("login.htm") 
				   && !url.endsWith("musician-signup.htm")
				  &&!url.endsWith("musicrecruitor-signup.htm")&& !url.endsWith("Check.htm")&& !url.endsWith("download")
				  && !url.endsWith("downloadRec") && !url.endsWith("forgotPasswordDetails.htm")&& !url.endsWith("forgotPassword.htm")
				  && !url.endsWith("report.xls") && !url.endsWith("home.htm"))
		  {
			
			  User user;
			   try
			   {
				   user= (User)request.getSession().getAttribute("user");
				   if(user.getRole().equals("admin") && url.contains("admin")){
					  
					   return true;
				   }
				   else{
					   user=null;
				   }
			   }
			   catch(Exception ex)
			   {
				   user=null;
			   }
			   try
			   {
				   user= (User)request.getSession().getAttribute("user");
				   if(user.getRole().equals("musician") && url.contains("musician")){
					   
					   return true;
				   }
				   else{
					   user=null;
				   }
			   }
			   catch(Exception ex)
			   {
				   user=null;
			   }
			   try
			   {
				   user= (User)request.getSession().getAttribute("user");
				   if(user.getRole().equals("musicRecruitor") && url.contains("musicrecruitor")){
					   
					   return true;
				   }
				   else{
					   user=null;
				   }
			   }
			   catch(Exception ex)
			   {
				   user=null;
			   }
			   if(user==null)
			   {
				   
			    response.sendRedirect("/jobportal/login.htm");
			    return false;
			   }   
		  }
		  return true;
		 		
	}
}
