package com.stackroute.keepnote.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/*
 * The class "Note" will be acting as the data model for the Note Table in the database. 
 * Please note that this class is annotated with @Entity annotation. 
 * Hibernate will scan all package for any Java objects annotated with the @Entity annotation. 
 * If it finds any, then it will begin the process of looking through that particular 
 * Java object to recreate it as a table in your database.
 */
@Entity
public class Note {
	/*
	 * This class should have eight fields
	 * (noteId,noteTitle,noteContent,noteStatus,createdAt,
	 * category,reminder,createdBy). Out of these eight fields, the field noteId
	 * should be primary key and auto-generated. This class should also contain the
	 * getters and setters for the fields along with the no-arg , parameterized
	 * constructor and toString method. The value of createdAt should not be
	 * accepted from the user but should be always initialized with the system date.
	 * annotate category and reminder field with @ManyToOne.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer noteId;
	private String noteTitle;
	private String noteContent;
	private String noteStatus;
	private Date noteCreatedAt;
	@ManyToOne
	private Category category;
	@ManyToOne
	private Reminder reminder;
	private String createdBy;


	public Note() {

	}


	public Note(Integer noteId, String noteTitle, String noteContent, String noteStatus, Date noteCreatedAt,
			Category category, Reminder reminder, String createdBy) {
		super();
		this.noteId = noteId;
		this.noteTitle = noteTitle;
		this.noteContent = noteContent;
		this.noteStatus = noteStatus;
		this.noteCreatedAt = noteCreatedAt;
		this.category = category;
		this.reminder = reminder;
		this.createdBy = createdBy;
	}


	public Integer getNoteId() {
		return noteId;
	}


	public void setNoteId(Integer noteId) {
		this.noteId = noteId;
	}


	public String getNoteTitle() {
		return noteTitle;
	}


	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}


	public String getNoteContent() {
		return noteContent;
	}


	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}


	public String getNoteStatus() {
		return noteStatus;
	}


	public void setNoteStatus(String noteStatus) {
		this.noteStatus = noteStatus;
	}


	public Date getNoteCreatedAt() {
		return noteCreatedAt;
	}


	public void setNoteCreatedAt(Date noteCreatedAt) {
		this.noteCreatedAt = noteCreatedAt;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public Reminder getReminder() {
		return reminder;
	}


	public void setReminder(Reminder reminder) {
		this.reminder = reminder;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	@Override
	public String toString() {
		return "Note [noteId=" + noteId + ", noteTitle=" + noteTitle + ", noteContent=" + noteContent + ", noteStatus="
				+ noteStatus + ", noteCreatedAt=" + noteCreatedAt + ", category=" + category + ", reminder=" + reminder
				+ ", createdBy=" + createdBy + "]";
	}
	/*public Note(int Int, String string, String string1, String string2, Date date, Category category, Reminder reminder,
			String string3) {

	}

	public int getNoteId() {
		return 0;
	}

	public void setNoteId(int Int) {

	}

	public String getNoteTitle() {
		return null;
	}

	public void setNoteTitle(String string) {

	}

	public String getNoteContent() {
		return null;
	}

	public void setNoteContent(String string) {

	}

	public void setNoteStatus(String string) {

	}

	public void setNoteCreatedAt(Date date) {
	}

	public void setCreatedBy(String string) {

	}

	public void setReminder(Reminder reminder) {

	}

	public void setCategory(Category category) {
	}*/

}
