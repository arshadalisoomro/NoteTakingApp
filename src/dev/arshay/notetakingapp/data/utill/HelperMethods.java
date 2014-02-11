/**
 * 
 */
package dev.arshay.notetakingapp.data.utill;

import java.util.Date;

import android.content.Context;
import android.text.format.DateUtils;
import dev.arshay.notetakingapp.NoteEditorActivity;
import dev.arshay.notetakingapp.data.NoteDataSource;
import dev.arshay.notetakingapp.model.Note;

/**
 * @author Arshad Ali
 *
 */
public class HelperMethods {
	
	public static String getDate(Context context, Date date) {		
		String formatedDate = DateUtils.formatDateTime(context, date.getTime(), DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_TIME);
		return formatedDate;		
	}
	
	public static void saveOrUpdate(NoteDataSource source, long noteId, String textValue, CharSequence date) {
		Note note;
		String text = null;
		try {
			if (noteId == 0) {
				note = new Note();
				text = textValue;
				if (!text.equals("")) {
					note.setText(text);
					note.setTime(date.toString());
					source.createNote(note);

				}
				
			} else {
				note = new Note();
				text = textValue;
				String time = date.toString();
				if (!text.equals("") && !time.equals("")) {
					long idOfNote = NoteEditorActivity.NOTE_ID;
					note.setId(idOfNote);
					note.setText(text);
					note.setTime(time);
					source.updateNote(note);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
