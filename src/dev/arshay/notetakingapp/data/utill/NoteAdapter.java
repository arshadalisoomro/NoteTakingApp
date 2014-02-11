package dev.arshay.notetakingapp.data.utill;

import dev.arshay.notetakingapp.R;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

public class NoteAdapter extends CursorAdapter {

	protected Activity activity = null;
	
	public NoteAdapter(Activity activity, Cursor c) {
		super(activity, c, true);
		// TODO Auto-generated constructor stub
		this.activity = activity;
	}


	@Override
	public void bindView(View view, Context arg1, Cursor cursor) {
		// TODO Auto-generated method stub
		NoteHolder holder = new NoteHolder(activity, view);
		holder.populateFrom(cursor);
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup root) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = activity.getLayoutInflater();
		View row = inflater.inflate(R.layout.row, root, false);
		
		NoteHolder holder = new NoteHolder(activity, row);
		row.setTag(holder);
		return row;
	}

}
