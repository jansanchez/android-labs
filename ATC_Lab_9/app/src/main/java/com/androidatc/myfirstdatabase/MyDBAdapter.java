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

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBAdapter {

	Context context;
	private myDBHelper dbHelper;
	private SQLiteDatabase db;

	private String DATABASE_NAME = "data";
	private int DATABASE_VERSION = 1;

	public MyDBAdapter(Context context) {
		this.context = context;
		dbHelper = new myDBHelper(context, DATABASE_NAME, null,
				DATABASE_VERSION);
	}

	public void open() {
		db = dbHelper.getWritableDatabase();
	}

	/**
	 * Insert a new student to DB.
	 * @param name
	 * @param faculty
	 */
	public void insertStudent(String name, int faculty) {
		ContentValues cv = new ContentValues();
		cv.put("name", name);
		cv.put("faculty", faculty);
		db.insert("students", null, cv);
	}

	/**
	 * 
	 * Deletes all students in the engineering faculty.
	 * 
	 */
	public void deleteAllEngineers() {
		db.delete("students", "faculty=1", null);
	}

	/**
	 * Returns a list of all students in the DB.
	 * @return A list of students.
	 */
	public ArrayList<String> selectAllStudents() {
		ArrayList<String> allStudents = new ArrayList<String>();
		Cursor cursor = db
				.query("students", null, null, null, null, null, null);

		if (cursor != null && cursor.moveToFirst()) {
			do {
				allStudents.add(cursor.getString(1));
			} while (cursor.moveToNext());
		}

		return allStudents;
	}

	public class myDBHelper extends SQLiteOpenHelper {
		public myDBHelper(Context context, String name, CursorFactory factory,
				int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			String query = "CREATE TABLE students (id integer primary key autoincrement, name text, faculty integer);";
			db.execSQL(query);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			String query = "DROP TABLE IF EXISTS students;";
			db.execSQL(query);
			onCreate(db);
		}
	}
}
