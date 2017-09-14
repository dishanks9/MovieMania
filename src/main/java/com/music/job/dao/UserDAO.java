package com.music.job.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.music.job.exception.UserException;
import com.music.job.pojo.MessageToAdmin;
import com.music.job.pojo.User;


public class UserDAO extends DAO {

	public UserDAO() {
	}

	public User get(String username, String password) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where username = :username and password = :password");
			q.setString("username", username);
			q.setString("password", password);
			User user = (User) q.uniqueResult();
			getSession().getTransaction().commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + username, e);
		}
	}
	
	public User getByUsername(String username) throws UserException {
		try {
			getSession().getTransaction().begin();
			Query q = getSession().createQuery("from User where username = :username");
			q.setString("username", username);
			User user = (User) q.uniqueResult();
			getSession().getTransaction().commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + username, e);
		}
	}
	
	public boolean saveUser(User user) throws Exception
	{
		try{
			getSession().getTransaction().begin();
			getSession().saveOrUpdate(user);
			getSession().getTransaction().commit();
			return true;
		}
		catch(HibernateException ex)
		{
			getSession().getTransaction().rollback();
			return false;
		
		}
	}
	
	public User queryUserById(long id)
            throws Exception {
        try {
            Criteria cr = getSession().createCriteria(User.class);
           
            cr.add(Restrictions.like("userId", id));
	        User user=(User)cr.uniqueResult();
            return user;
        } catch (HibernateException e) {
        	close();
            throw new Exception("Could not get User " + id, e);
        }
    }

	public boolean delete(User user) throws UserException {
		try {
			begin();
			getSession().delete(user);
			commit();
			return true;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not delete user " + user.getUsername(), e);
		}
	}
	
	public ArrayList<User> getAllUsers()throws Exception{
		try{
			Query q = getSession().createQuery("From User");
               
            ArrayList<User> user=(ArrayList<User>)q.list();
            return user;
		}
		catch(HibernateException e)
		{
			
			throw new Exception("Could not get User ", e);
		}
		
	}
}