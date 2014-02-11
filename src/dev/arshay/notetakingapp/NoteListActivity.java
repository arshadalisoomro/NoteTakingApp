package dev.arshay.notetakingapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import dev.arshay.notetakingapp.data.NoteDataSource;
import dev.arshay.notetakingapp.data.columns.NoteColumns;
import dev.arshay.notetakingapp.data.utill.NoteConstants;
import dev.arshay.notetakingapp.data.utill.NoteAdapter;
import dev.arshay.notetakingapp.model.Note;

public class NoteListActivity extends SherlockListActivity {

	protected ActionBar nActionBar = null;
	protected Cursor nNoteCursor = null;
	protected Note nNote = null;
	protected NoteDataSource nDataSource = null;
	protected NoteAdapter nAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_note);

		nActionBar = getSupportActionBar();
		nActionBar.setDisplayShowTitleEnabled(true);

		try {
			nDataSource = new NoteDataSource(NoteListActivity.this);
			nDataSource.openDB();
			nNoteCursor = nDataSource.findAll();
			if (nNoteCursor.moveToFirst()) {
				new GetNotesTask().execute((Cursor[]) null);
			}
		} catch (Exception e) {
			Toast.makeText(NoteListActivity.this, "Error : " + e.getMessage(),
					Toast.LENGTH_LONG).show();
		}

	}

	@Override
	protected void onStart() {
		super.onStart();
		new GetNotesTask().execute((Cursor[]) null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		new MenuInflater(NoteListActivity.this).inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.action_add) {
			creatNote();
			return true;
		}
		return false;
	}

	protected void creatNote() {
		Intent intent = new Intent(NoteListActivity.this, NoteEditorActivity.class);
		startActivityForResult(intent, NoteConstants.ADD_REQ);
	}

	protected class GetNotesTask extends AsyncTask<Cursor, Object, Cursor> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			nDataSource.openDB();
		}

		@Override
		protected Cursor doInBackground(Cursor... arg0) {
			return nDataSource.findAll();
		}

		@Override
		protected void onPostExecute(Cursor result) {
			nAdapter = new NoteAdapter(NoteListActivity.this, result);

			setListAdapter(nAdapter);
			getListView().setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> adapterView, View row,
						int position, long idInList) {
					Intent intent = new Intent(NoteListActivity.this,
							NoteEditorActivity.class);
					intent.putExtra(NoteColumns._ID, idInList);
					startActivityForResult(intent, NoteConstants.UPDATE_REQ);
				}
			});
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		nDataSource.closeDB();
	}
}
