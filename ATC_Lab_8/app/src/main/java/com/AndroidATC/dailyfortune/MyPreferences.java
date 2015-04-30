/**
 * Copyright � 2014 Android ATC.
 * 
 * Author: Android ATC Training Team.
 * 
 * Source code in this project is provided for trainers of  
 * course AND-401 titled "Android Application Development".
 * 
 * The is the source code for Lab 8: Daily Fortune Application.
 * 
 */
package com.AndroidATC.dailyfortune;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;



public class MyPreferences {
	
	// Shared Preferences
	SharedPreferences pref;

	// Editor for Shared preferences
	Editor editor;

	// Context
	Context _context;

	// Shared pref mode
	int PRIVATE_MODE = 0;

	// Sharedpref file name
	private static final String PREF_NAME = "DailyFortune";

	
	private static final String IS_FIRSTTIME = "IsFirstTime";

	public static final String UserName = "name";
	


	
	// Constructor
	public MyPreferences(Context context) {
		this._context = context;
		pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = pref.edit();
	}


	
	public boolean isFirstTime() {

        return pref.getBoolean(IS_FIRSTTIME, true);
		}
	
	public void setOld(boolean b){
		if(b){
		editor.putBoolean(IS_FIRSTTIME, false);
		editor.commit();}
	}

	public String getUserName(){
		return pref.getString(UserName, "");
	}
	public void setUserName(String name){
		editor.putString(UserName, name);
		editor.commit();
	}

}
