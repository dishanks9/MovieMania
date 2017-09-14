package com.music.job.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import com.music.job.exception.MusicException;
import com.music.job.pojo.Applications;
import com.music.job.pojo.Events;
import com.music.job.pojo.Jobs;
import com.music.job.pojo.MusicRecruitor;
import com.music.job.pojo.Musician;
import com.music.job.pojo.User;


public class MusicianDAO extends DAO {
	public ArrayList<Jobs> listOfJobs() throws Exception{
		try{
			Query q = getSession().createQuery("From Jobs");   
            ArrayList<Jobs> jobs=(ArrayList<Jobs>)q.list();
            return jobs;
		}
		catch(HibernateException e)
		{
			getSession().getTransaction().rollback();
			throw new Exception("Could not get jobs ", e);
		}
	}
	
	public Jobs getJob(int jobId) throws Exception
	{
		try {
            Criteria cr = getSession().createCriteria(Jobs.class);
            cr.add(Restrictions.eq("jobId", jobId));
            Jobs job=(Jobs)cr.uniqueResult();
            return job;
        } catch (HibernateException e) {
            throw new Exception("Could not get Job Details ", e);
        }
	}
	
	public boolean saveApplication(Applications application)
	{
		try{
			getSession().getTransaction().begin();
			getSession().saveOrUpdate(application);
			getSession().getTransaction().commit();
			return true;
		}
		catch(HibernateException ex)
		{
			getSession().getTransaction().rollback();
			return false;
		}
	}
	
	public ArrayList<Applications> getMyApplications(Musician ms) throws Exception
	{
		try{
			Query q = getSession().createQuery("From Applications where user = :user");
            q.setEntity("user",ms);    
            ArrayList<Applications> applications=(ArrayList<Applications>)q.list();
            return applications;
		}
		catch(HibernateException e)
		{
			getSession().getTransaction().rollback();
			throw new Exception("Could not get applications for " + ms.getFirstName(), e);
		}
		
	}
	
	public ArrayList<Events> listOfEvents() throws Exception{
		try{
			Query q = getSession().createQuery("From Events");   
            ArrayList<Events> event=(ArrayList<Events>)q.list();
            return event;
		}
		catch(HibernateException e)
		{
			getSession().getTransaction().rollback();
			throw new Exception("Could not get events ", e);
		}
	}
	
	public Events getEvent(int eventId) throws Exception
	{
		try {
            Criteria cr = getSession().createCriteria(Events.class);
            cr.add(Restrictions.eq("eventId", eventId));
            Events event=(Events)cr.uniqueResult();
            return event;
        } catch (HibernateException e) {
            throw new Exception("Could not get event Details ", e);
        }
	}
	
	public boolean addEvent(Events event)
	{
		try{
			getSession().getTransaction().begin();
			getSession().update(event);
			getSession().getTransaction().commit();
			return true;
		}
		catch(Exception ex)
		{
			getSession().getTransaction().rollback();
			return false;
		}
	}
	
	public ArrayList<MusicRecruitor> getRecruitor() throws Exception
	{
		try {
            Criteria cr = getSession().createCriteria(User.class);
            cr.add(Restrictions.eq("role", "musicRecruitor"));
            ArrayList<MusicRecruitor> mr=(ArrayList<MusicRecruitor>)cr.list();
            
            return mr;
        } catch (HibernateException e) {
            throw new Exception("Could not get recruitor Details ", e);
        }
	}
	
	public MusicRecruitor checkRecruitor(long userId,long muserId) throws Exception{
		try{
			Query q = getSession().createQuery("From MusicRecruitor mr join mr.mConnections m where mr.userId =:userId and m.userId =:muserId");  
			q.setLong("userId", userId);
			q.setLong("muserId", muserId);
			MusicRecruitor mr=(MusicRecruitor)q.uniqueResult();
            return mr;
		}
		catch(HibernateException e)
		{
			
			throw new Exception("Could not get results ", e);
		}
	}
	
	public boolean addMusician(Musician musician)throws Exception
	{
		try{
			getSession().getTransaction().begin();
			getSession().saveOrUpdate(musician);
			getSession().getTransaction().commit();
			return true;
		}
		catch(HibernateException ex)
		{
			getSession().getTransaction().rollback();
			return false;
			
		}
	}
	
	public boolean getAppliedJob(User user,Jobs job)throws Exception{
		try{
			Criteria cr=getSession().createCriteria(Applications.class);
			cr.add(Restrictions.eq("job", job));
			cr.add(Restrictions.eq("user", user));
			Applications application=(Applications)cr.uniqueResult();
			if(application!=null){
				return true;
			}
			else{
				return false;
			}
		}
		catch(HibernateException e)
		{
			
			throw new Exception("Could not get applied job results ", e);
		}
	}
	
	public boolean checkEvent(long userId,long muserId) throws Exception{
		try{
			Query q = getSession().createQuery("From Musician mr join mr.event m where mr.userId =:muserId and m.eventId =:eventId");  
			q.setLong("muserId", userId);
			q.setLong("eventId", muserId);
			Object mr=(Object)q.uniqueResult();
			if(mr!=null){
				return false;
			}
			else{
				return true;
			}
		}
		catch(HibernateException e)
		{
			
			throw new Exception("Could not get registered event results ", e);
		}
	}
	
	public boolean checkEmail(String emailId) throws Exception{
		try{
			Criteria cr=getSession().createCriteria(User.class);
			cr.add(Restrictions.eq("emailId", emailId));
			User user=(User)cr.uniqueResult();
			if(user!=null){
				return false;
			}
			else{
				return true;
			}
		}
		catch(HibernateException e)
		{
			
			throw new Exception("Could not get already used email results ", e);
		}
	}
	
	public boolean checkUsername(String userName) throws Exception{
		try{
			Criteria cr=getSession().createCriteria(User.class);
			cr.add(Restrictions.eq("username", userName));
			User user=(User)cr.uniqueResult();
			if(user!=null){
				return false;
			}
			else{
				return true;
			}
		}
		catch(HibernateException e)
		{
			
			throw new Exception("Could not get already usd username results ", e);
		}
	}
}
