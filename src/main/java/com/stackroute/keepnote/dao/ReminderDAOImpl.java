package com.stackroute.keepnote.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stackroute.keepnote.exception.ReminderNotFoundException;
import com.stackroute.keepnote.model.Reminder;

/*
 * This class is implementing the UserDAO interface. This class has to be annotated with 
 * @Repository annotation.
 * @Repository - is an annotation that marks the specific class as a Data Access Object, 
 * thus clarifying it's role.
 * @Transactional - The transactional annotation itself defines the scope of a single database 
 * 					transaction. The database transaction happens inside the scope of a persistence 
 * 					context.  
 * */

@Repository
@Transactional
public class ReminderDAOImpl implements ReminderDAO {

	/*
	 * Autowiring should be implemented for the SessionFactory.(Use
	 * constructor-based autowiring.
	 */
	@Autowired
	SessionFactory sessionFactory;

	public ReminderDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}
	/*
	 * Create a new reminder
	 */
	public boolean createReminder(Reminder reminder) {

		if(null == reminder || null == reminder.getReminderId()) {
			return false;
		}
		sessionFactory.getCurrentSession().save(reminder);
		return true;
	}
	/*
	 * Update an existing reminder
	 */
	public boolean updateReminder(Reminder reminder) {

		if(null == reminder || null == reminder.getReminderId()) {
			return false;
		}
		sessionFactory.getCurrentSession().update(reminder);
		return true;
	}
	/*
	 * Remove an existing reminder
	 */
	public boolean deleteReminder(int reminderId) {

		boolean flag = false;
		try {
			Reminder reminder = getReminderById(reminderId);
			if(null == reminder) {
				flag = false;
			}else {
				sessionFactory.getCurrentSession().delete(reminder);
				flag = true;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (ReminderNotFoundException e) {
			e.printStackTrace();
		}
		return flag;
	}
	/*
	 * Retrieve details of a specific reminder
	 */
	public Reminder getReminderById(int reminderId) throws ReminderNotFoundException {
		
		Reminder reminder = sessionFactory.getCurrentSession().get(Reminder.class, reminderId);
		if(null == reminder) {
			throw new ReminderNotFoundException("ReminderNotFoundException");
		}
		return reminder;
	}
	/*
	 * Retrieve details of all reminders by userId
	 */
	public List<Reminder> getAllReminderByUserId(String userId) {
		
		if(null == userId || userId.isEmpty()) {
			return null;
		}
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(Reminder.class);
		List<Reminder> reminderList =  cr.add(Restrictions.eq("reminderCreatedBy", userId)).list();
		return reminderList;
	}
}
