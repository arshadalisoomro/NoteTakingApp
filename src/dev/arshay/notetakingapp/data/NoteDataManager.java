package dev.arshay.notetakingapp.data;

import android.database.Cursor;

public class NoteDataManager {

	public static String getNoteId(Cursor cursor) {
		// TODO Auto-generated method stub
		return cursor.getString(0);
	}
	
	public static String getNoteText(Cursor cursor) {
		// TODO Auto-generated method stub
		return cursor.getString(1);
	}
	public static String getNoteTime(Cursor cursor) {
		// TODO Auto-generated method stub
		return cursor.getString(2);
	}

	public static int getNoteLanguage(Cursor cursor) {
		// TODO Auto-generated method stub
		return cursor.getInt(3);
	}

}
