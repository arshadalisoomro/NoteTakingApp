package dev.arshay.notetakingapp.data.table;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import dev.arshay.notetakingapp.data.columns.NoteColumns;

public class NoteTable {
	public static final String NOTE_TABLE = "note";

	public static void onCreate(SQLiteDatabase db) {
		try {
			final String sql = "CREATE TABLE IF NOT EXISTS "
					+ NoteTable.NOTE_TABLE + "(" + NoteColumns._ID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ NoteColumns.NOTE_TEXT + " TEXT, "
					+ NoteColumns.NOTE_TIME + " TEXT)";

			db.execSQL(sql);
		} catch (SQLiteException e) {
			e.printStackTrace();
		}
	}

	public static void onUpgrade(SQLiteDatabase db, int oldVersion,
			int newVersion) {
		try {
			final String sql = "DROP TABLE" + NoteTable.NOTE_TABLE;
			db.execSQL(sql);
			onCreate(db);
		} catch (SQLiteException e) {
			e.printStackTrace();
		}
	}
}
