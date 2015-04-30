/**
 * Copyright © 2014 Android ATC.
 * 
 * Author: Android ATC Training Team.
 * 
 * Source code in this project is provided for trainers of  
 * course AND-401 titled "Android Application Development".
 * 
 * The is the source code for Lab 9: Creating SQLite Database.
 * 
 */
package com.androidatc.myfirstdatabase;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

public class MainActivity extends Activity {
	MyDBAdapter dbAdapter;
	ListView list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final Spinner faculties = (Spinner) findViewById(R.id.faculties_spinner);
		final EditText studentName = (EditText) findViewById(R.id.student_name);
		Button addStudent = (Button) findViewById(R.id.add_student);
		Button deleteEngineers = (Button) findViewById(R.id.delete_engineers);
		list = (ListView) findViewById(R.id.student_list);

		dbAdapter = new MyDBAdapter(MainActivity.this);
		dbAdapter.open();

		// Fill the spinner with 3 faculty values
		String[] allFaculties = { "Engineering", "Business", "Arts" };
		faculties.setAdapter(new ArrayAdapter<String>(MainActivity.this,
				android.R.layout.simple_list_item_1, allFaculties));

		loadList();

		addStudent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dbAdapter.insertStudent(studentName.getText().toString(),
						faculties.getSelectedItemPosition() + 1);
				loadList();
			}
		});

		deleteEngineers.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dbAdapter.deleteAllEngineers();
				loadList();
			}
		});
			

	}

	public void loadList() {
		ArrayList<String> allStudents = new ArrayList<String>();
		allStudents = dbAdapter.selectAllStudents();

		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				MainActivity.this, android.R.layout.simple_list_item_1,
				allStudents);
		list.setAdapter(adapter);
	}

}
