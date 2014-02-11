/**
 * 
 */
package dev.arshay.notetakingapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import dev.arshay.notetakingapp.data.table.NoteTable;
import dev.arshay.notetakingapp.data.utill.NoteConstants;

/**
 * @author Arshad Ali
 *
 */
public class NoteOpenHelper extends SQLiteOpenHelper {

	public NoteOpenHelper(Context context) {
		super(context, NoteConstants.DB_NAME, null, NoteConstants.DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		NoteTable.onCreate(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		NoteTable.onUpgrade(db, oldVersion, newVersion);
		onCreate(db);
	}

}
