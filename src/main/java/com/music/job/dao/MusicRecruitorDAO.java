package com.music.job.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.music.job.exception.UserException;
import com.music.job.pojo.Applications;
import com.music.job.pojo.Jobs;
import com.music.job.pojo.MusicRecruitor;
import com.music.job.pojo.Musician;
import com.music.job.pojo.User;

public class MusicRecruitorDAO extends DAO {
	public boolean saveJobPosting(Jobs job)
	{
		try{
			getSession().getTransaction().begin();
			getSession().saveOrUpdate(job);
			getSession().getTransaction().commit();
			return true;
		}
		catch(HibernateException ex)
		{
			getSession().getTransaction().rollback();
			return false;
		}
	}
	
	public ArrayList<Jobs> getMyJobs(MusicRecruitor mr) throws Exception
	{
		try{
			Query q = getSession().createQuery("From Jobs where user = :user");
            q.setEntity("user",mr);    
            ArrayList<Jobs> jobs=(ArrayList<Jobs>)q.list();
            return jobs;
		}
		catch(HibernateException e)
		{
			getSession().getTransaction().rollback();
			throw new Exception("Could not get jobs posted " + mr.getFirstName(), e);
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
	
	public Applications get(int job_Id, long user_fk) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from Applications where job_id = :job_id and user_fk = :user_fk");
			q.setInteger("job_id", job_Id);
			q.setLong("user_fk", user_fk);
			Applications application  = (Applications) q.uniqueResult();
			
			return application;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get application details " + job_Id, e);
		}
	}
	
	public ArrayList<Musician> getMusician() throws Exception
	{
		try {
            Criteria cr = getSession().createCriteria(User.class);
            cr.add(Restrictions.eq("role", "musician"));
            ArrayList<Musician> mr=(ArrayList<Musician>)cr.list();
            
            return mr;
        } catch (HibernateException e) {
            throw new Exception("Could not get musician Details ", e);
        }
	}
	
	public boolean addMusicianRec(MusicRecruitor rec)throws Exception
	{
		try{
			getSession().getTransaction().begin();
			getSession().saveOrUpdate(rec);
			getSession().getTransaction().commit();
			return true;
		}
		catch(Exception ex)
		{
			getSession().getTransaction().rollback();
			return false;
			
		}
	}
}
