package dev.arshay.notetakingapp.data.utill;

import dev.arshay.notetakingapp.R;
import dev.arshay.notetakingapp.data.NoteDataManager;
import android.app.Activity;
import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class NoteHolder {

	protected Activity activity = null;
	protected TextView textView = null;
	protected ImageView note = null;
	protected ImageView pen = null;
	public NoteHolder(Activity activity, View view) {
		// TODO Auto-generated constructor stub
		this.activity = activity;
		textView = (TextView) view.findViewById(R.id.my_row);
		//note = (ImageView) view.findViewById(R.id.note_iv);
		//pen = (ImageView) view.findViewById(R.id.pencil_iv);
		
	}
	
	public void populateFrom(Cursor cursor){
		textView.setText(NoteDataManager.getNoteText(cursor));
		/*Bitmap bitN = BitmapFactory.decodeResource(activity.getResources(), R.drawable.note);
		Bitmap bitP = BitmapFactory.decodeResource(activity.getResources(), R.drawable.pencil);
		note.setImageBitmap(bitN);
		pen.setImageBitmap(bitP);*/
	}

}
