package dev.arshay.notetakingapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import dev.arshay.notetakingapp.data.columns.NoteColumns;
import dev.arshay.notetakingapp.data.table.NoteTable;
import dev.arshay.notetakingapp.data.utill.NoteConstants;
import dev.arshay.notetakingapp.model.Note;

public class NoteDataSource {
	protected SQLiteOpenHelper dbHelper = null;
	public SQLiteDatabase database = null;

	public NoteDataSource(Context context) {
		// TODO Auto-generated constructor stub
		dbHelper = new NoteOpenHelper(context);

	}

	public void openDB() {
		// TODO Auto-generated method stub
		database = dbHelper.getWritableDatabase();
	}

	public void createNote(Note note) {
		ContentValues values = new ContentValues();

		values.put(NoteColumns.NOTE_TEXT, note.getText());
		values.put(NoteColumns.NOTE_TIME, note.getTime());

		try {
			database.insert(NoteTable.NOTE_TABLE, null, values);
		} catch (SQLiteException e) {
			// TODO: handle exception
		}

	}

	public void updateNote(Note note) {
		String[] args = { String.valueOf(note.getId()) };
		String where = NoteColumns._ID + " = ? ";
		ContentValues values = new ContentValues();

		values.put(NoteColumns.NOTE_TEXT, note.getText());
		values.put(NoteColumns.NOTE_TIME, note.getTime());

		try {
			database.update(NoteTable.NOTE_TABLE, values, where, args);
		} catch (SQLiteException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public boolean delete(long id) {
		String[] args = { String.valueOf(id) };
		String where = NoteColumns._ID + " = ? ";
		int result = database.delete(NoteTable.NOTE_TABLE, where, args);

		return (result == 1);
	}

	public Cursor getNoteById(long noteId) {
		String args[] = { String.valueOf(noteId) };
		String where = " WHERE " + NoteColumns._ID + " = ? ";
		String query = "SELECT * FROM " + NoteTable.NOTE_TABLE + where;
		return (dbHelper.getReadableDatabase().rawQuery(query, args));
	}

	public Cursor findAll() {

		return (dbHelper.getReadableDatabase().query(NoteTable.NOTE_TABLE,
				NoteConstants.allColumns, null, null, null, null, NoteColumns._ID));

	}

	public void closeDB() {
		// TODO Auto-generated method stub
		database.close();
	}
}
