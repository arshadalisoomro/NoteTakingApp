package dev.arshay.notetakingapp.data.utill;

import dev.arshay.notetakingapp.data.columns.NoteColumns;

public class NoteConstants {
	public static final String DB_NAME = "my_notes";
	public static final int DB_VERSION = 1;
	public static final String TAG = "MY NOTES";
	public static final int ADD_REQ = 1;
	public static final int UPDATE_REQ = 505;
	public static final String[] allColumns = {
		NoteColumns._ID,
		NoteColumns.NOTE_TEXT,
		NoteColumns.NOTE_TIME
	};
}