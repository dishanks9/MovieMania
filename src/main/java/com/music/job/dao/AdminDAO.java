package com.music.job.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.music.job.pojo.Events;
import com.music.job.pojo.MessageToAdmin;
import com.music.job.pojo.MusicRecruitor;
import com.music.job.pojo.User;

public class AdminDAO extends DAO {
	public ArrayList<MessageToAdmin> getMesaages() throws Exception
	{
		try{
			Query q = getSession().createQuery("From MessageToAdmin");     
            ArrayList<MessageToAdmin> message=(ArrayList<MessageToAdmin>)q.list();
            return message;
		}
		catch(HibernateException e)
		{
			throw new Exception("Could not get messages ", e);
		}
		
	}
	
	public MessageToAdmin queryMessageById(long id)
            throws Exception {
        try {
            Criteria cr = getSession().createCriteria(MessageToAdmin.class);
            cr.add(Restrictions.like("id", id));
            MessageToAdmin messageToAdmin=(MessageToAdmin)cr.uniqueResult();
            return messageToAdmin;
        } catch (HibernateException e) {
        	close();
            throw new Exception("Could not get message " + id, e);
        }
    }
	
	public int updateStatus(long userId)throws Exception{
		try{
			Query q=getSession().createQuery("update User set status= :status where userId= :userId");
			q.setString("status", "active");
			q.setLong("userId", userId);
			int result=q.executeUpdate();
			
			return result;
		}
		catch(HibernateException e){
			throw new Exception("could not update status",e);
		}
	}
	
	public boolean saveEvent(Events event) throws Exception
	{
		try{
			getSession().getTransaction().begin();
			getSession().saveOrUpdate(event);
			getSession().getTransaction().commit();
			return true;
		}
		catch(HibernateException ex)
		{
			getSession().getTransaction().rollback();
			throw new Exception("Could not add event ", ex);
		
		}
	}
	
	public ArrayList<Events> getEvents() throws Exception
	{
		try{
			Query q = getSession().createQuery("From Events");
               
            ArrayList<Events> event=(ArrayList<Events>)q.list();
            return event;
		}
		catch(HibernateException e)
		{
			
			throw new Exception("Could not get events ", e);
		}
		
	}
	
	public Events queryEventById(int eventId)
            throws Exception {
        try {
            Criteria cr = getSession().createCriteria(Events.class);
            cr.add(Restrictions.like("eventId", eventId));
            Events event=(Events)cr.uniqueResult();
            return event;
        } catch (HibernateException e) {
        	close();
            throw new Exception("Could not get Event " + eventId, e);
        }
    }
	
}
