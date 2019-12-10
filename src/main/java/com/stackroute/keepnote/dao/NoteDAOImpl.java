package com.stackroute.keepnote.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stackroute.keepnote.exception.NoteNotFoundException;
import com.stackroute.keepnote.model.Note;

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
public class NoteDAOImpl implements NoteDAO {

	/*
	 * Autowiring should be implemented for the SessionFactory.(Use
	 * constructor-based autowiring.
	 */
	@Autowired
	SessionFactory sessionFactory;

	public NoteDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}
	/*
	 * Create a new note
	 */
	public boolean createNote(Note note) {

		if(null == note) {
			return false;
		}
		sessionFactory.getCurrentSession().save(note);
		return true;
	}
	/*
	 * Remove an existing note
	 */
	public boolean deleteNote(int noteId) {

		try {
			Note note = getNoteById(noteId);
			if(null == note) {
				return false;
			}else {
				sessionFactory.getCurrentSession().delete(note);;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (NoteNotFoundException e) {
			e.printStackTrace();
		}
		return true;
	}
	/*
	 * Retrieve details of all notes by userId
	 */
	public List<Note> getAllNotesByUserId(String userId) {

		if(null == userId || userId.isEmpty()) {
			return null;
		}
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(Note.class);
		List<Note> noteList =  cr.add(Restrictions.eq("createdBy", userId)).list();
		return noteList;
	}
	/*
	 * Retrieve details of a specific note
	 */
	public Note getNoteById(int noteId) throws NoteNotFoundException {

		Note note = sessionFactory.getCurrentSession().get(Note.class, noteId);
		if(null == note) {
			throw new NoteNotFoundException("NoteNotFoundException");
		}
		return note;
	}
	/*
	 * Update an existing note
	 */
	public boolean UpdateNote(Note note) {

		if(null == note || null == note.getNoteId()) {
			return false;
		}
		sessionFactory.getCurrentSession().update(note);
		return true;
	}
}
