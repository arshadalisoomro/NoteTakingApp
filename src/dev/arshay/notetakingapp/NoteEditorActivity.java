package dev.arshay.notetakingapp;

import java.util.Date;

import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import dev.arshay.notetakingapp.data.NoteDataManager;
import dev.arshay.notetakingapp.data.NoteDataSource;
import dev.arshay.notetakingapp.data.columns.NoteColumns;
import dev.arshay.notetakingapp.data.utill.HelperMethods;
import dev.arshay.notetakingapp.model.Note;

public class NoteEditorActivity extends SherlockActivity {

	protected ActionBar nActionBar = null;
	protected Button nNextButton, nPreviouseButton, nTrashButton = null;
	
	protected EditText nNoteEditText = null;
	protected TextView nDateTextView = null;
	protected Note nNote = null;
	protected NoteDataSource nDataSource = null;

	public static long NOTE_ID = 0;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_editor);

		nActionBar = getSupportActionBar();
		nActionBar.setDisplayShowTitleEnabled(true);
		nActionBar.setHomeButtonEnabled(true);
		nActionBar.setIcon(R.drawable.home);

		nDataSource = new NoteDataSource(NoteEditorActivity.this);
		nDataSource.openDB();

		nPreviouseButton = (Button) findViewById(R.id.previous);
		nNextButton = (Button) findViewById(R.id.next);
		nTrashButton = (Button) findViewById(R.id.trash);
		nNoteEditText = (EditText) this.findViewById(R.id.editText1);
		nDateTextView = (TextView) this.findViewById(R.id.date);
		nDateTextView.setText(HelperMethods.getDate(NoteEditorActivity.this, new Date()));

		NOTE_ID = getIntent().getLongExtra(NoteColumns._ID, 0);

		if (NOTE_ID != 0) {
			Cursor cursor = nDataSource.getNoteById(NOTE_ID);
			if (cursor.moveToFirst()) {
				nNextButton.setVisibility(View.VISIBLE);
				nPreviouseButton.setVisibility(View.VISIBLE);
				nTrashButton.setVisibility(View.VISIBLE);
				nNoteEditText.setText(NoteDataManager.getNoteText(cursor));
				nDateTextView.setText(NoteDataManager.getNoteTime(cursor));
			}
		}
	}

	public void previouseNote(View view) {
		try {
			new AsyncTask<Cursor, Void, Cursor>() {

				@Override
				protected void onPreExecute() {
					super.onPreExecute();
					nDataSource.openDB();
				}

				@Override
				protected Cursor doInBackground(Cursor... arg0) {
					return nDataSource.getNoteById(--NOTE_ID);
				}

				@Override
				protected void onPostExecute(Cursor result) {

					if (result.moveToNext()) {
						nNoteEditText.setText(NoteDataManager.getNoteText(result));
						nDateTextView.setText(NoteDataManager.getNoteTime(result));
					}

				}

			}.execute((Cursor[]) null);
		} catch (CursorIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	public void trashNote(View view) {
		// Annonymo class
		new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... arg0) {
				nDataSource.delete(NOTE_ID);
				finish();
				return null;
			}

		}.execute((Void[]) null);
	}

	public void nextNote(View view) {

		try {
			new AsyncTask<Cursor, Void, Cursor>() {
				@Override
				protected void onPreExecute() {
					super.onPreExecute();
					nDataSource.openDB();
				};

				@Override
				protected Cursor doInBackground(Cursor... arg0) {
					return nDataSource.getNoteById(++NOTE_ID);
				}

				@Override
				protected void onPostExecute(Cursor result) {

					if (result.moveToNext()) {
						nNoteEditText.setText(NoteDataManager.getNoteText(result));
						nDateTextView.setText(NoteDataManager.getNoteTime(result));
					}

				}

			}.execute((Cursor[]) null);
		} catch (CursorIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onBackPressed() {
		saveAndFinish(nNoteEditText.getText().toString());
	}

	private void saveAndFinish(final String noteText) {

		try {
			new AsyncTask<Void, Void, Void>() {
				
				
				@Override
				protected Void doInBackground(Void... arg0) {
					
					if (noteText != null) {
						String dateNTime = HelperMethods.getDate(NoteEditorActivity.this, new Date());
						HelperMethods.saveOrUpdate(nDataSource, NOTE_ID, noteText,
								dateNTime);
					}else{
						Toast.makeText(NoteEditorActivity.this, "Nothing to save", Toast.LENGTH_SHORT).show();
					}
					return null;
				}

				@Override
				protected void onPostExecute(Void result) {
					setResult(RESULT_OK);
					finish();
				}

			}.execute((Void[]) null);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		new MenuInflater(NoteEditorActivity.this).inflate(R.menu.help, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			saveAndFinish(nNoteEditText.getText().toString());
		}

		return true;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		nDataSource.closeDB();
	}
}
