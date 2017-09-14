package com.music.job.dao;

import org.hibernate.HibernateException;

import com.music.job.pojo.MessageToAdmin;

public class MessageDAO extends DAO{
	public boolean saveMessage(MessageToAdmin message)
	{
		try{
			getSession().getTransaction().begin();
			getSession().saveOrUpdate(message);
			getSession().getTransaction().commit();
			return true;
		}
		catch(HibernateException ex)
		{
			getSession().getTransaction().rollback();
			return false;
		}
	}
}
