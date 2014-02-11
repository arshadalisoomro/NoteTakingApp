package dev.arshay.notetakingapp.model;

public class Note {
	
	protected long id = 0;
	protected String time = null;
	protected String text = null;

	/**
	 * @return the key
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

}
