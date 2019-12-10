package com.stackroute.keepnote.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.keepnote.dao.CategoryDAO;
import com.stackroute.keepnote.dao.NoteDAO;
import com.stackroute.keepnote.dao.ReminderDAO;
import com.stackroute.keepnote.exception.CategoryNotFoundException;
import com.stackroute.keepnote.exception.NoteNotFoundException;
import com.stackroute.keepnote.exception.ReminderNotFoundException;
import com.stackroute.keepnote.model.Category;
import com.stackroute.keepnote.model.Note;
import com.stackroute.keepnote.model.Reminder;

/*
 * Service classes are used here to implement additional business logic/validation 
 * This class has to be annotated with @Service annotation.
 * @Service - It is a specialization of the component annotation. It doesn�t currently 
 * provide any additional behavior over the @Component annotation, but it�s a good idea 
 * to use @Service over @Component in service-layer classes because it specifies intent 
 * better. Additionally, tool support and additional behavior might rely on it in the 
 * future.
 * */
@Service
public class NoteServiceImpl implements NoteService {

	/*
	 * Autowiring should be implemented for the NoteDAO,CategoryDAO,ReminderDAO.
	 * (Use Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword.
	 */
	@Autowired
	NoteDAO noteDAOImpl;
	@Autowired
	CategoryDAO categoryDAOImpl;
	@Autowired
	ReminderDAO reminderDAOImpl;

	/*
	 * This method should be used to save a new note.
	 */
	public NoteServiceImpl(NoteDAO noteDAOImpl, CategoryDAO categoryDAOImpl, ReminderDAO reminderDAOImpl) {
		super();
		this.noteDAOImpl = noteDAOImpl;
		this.categoryDAOImpl = categoryDAOImpl;
		this.reminderDAOImpl = reminderDAOImpl;
	}

	public boolean createNote(Note note) throws ReminderNotFoundException, CategoryNotFoundException {

		Reminder reminder = note.getReminder();
		Category category = note.getCategory();

		try {
			if(null != reminder) {
				reminderDAOImpl.getReminderById(reminder.getReminderId());
			}
		}catch (Exception e) {
			throw new ReminderNotFoundException("ReminderNotFoundException");
		}
		try {
			if(null != category) {
				categoryDAOImpl.getCategoryById(category.getCategoryId());
			}
		}catch (Exception e) {
			throw new CategoryNotFoundException("ReminderNotFoundException");
		}
		return noteDAOImpl.createNote(note);
	}

	/* This method should be used to delete an existing note. */

	public boolean deleteNote(int noteId) throws NoteNotFoundException {

		return noteDAOImpl.deleteNote(noteId);
	}
	/*
	 * This method should be used to get a note by userId.
	 */
	public List<Note> getAllNotesByUserId(String userId) {

		return noteDAOImpl.getAllNotesByUserId(userId);
	}

	/*
	 * This method should be used to get a note by noteId.
	 */
	public Note getNoteById(int noteId) throws NoteNotFoundException {

		Note note = noteDAOImpl.getNoteById(noteId);
		if(null == note) {
			throw new NoteNotFoundException("NoteNotFoundException");
		}
		return note;
	}
	/*
	 * This method should be used to update a existing note.
	 */
	public Note updateNote(Note note, int id)
			throws ReminderNotFoundException, NoteNotFoundException, CategoryNotFoundException {

		Reminder reminder = note.getReminder();
		Category category = note.getCategory();
		Note noteVo  = noteDAOImpl.getNoteById(id);

		if(null == noteVo) {
			throw new NoteNotFoundException("NoteNotFoundException");
		}
		noteDAOImpl.UpdateNote(noteVo);
		try {
			if(null != reminder) {
				reminderDAOImpl.getReminderById(reminder.getReminderId());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if(null != category) {
				categoryDAOImpl.getCategoryById(category.getCategoryId());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return note;
	}
}
