/**
 * Copyright © 2014 Android ATC.
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

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		MyPreferences pref = new MyPreferences(MainActivity.this);
		
		if(!pref.isFirstTime()){
			Intent i = new Intent(getApplicationContext(),FortuneActivity.class);
			i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivity(i);
			finish();
		}
		
	}

 
	public void SaveUserName(View v){
		
		EditText usrName = (EditText)findViewById(R.id.editText1);
		
		MyPreferences pref = new MyPreferences(MainActivity.this);
		pref.setUserName(usrName.getText().toString().trim());
		
		Intent i = new Intent(getApplicationContext(),FortuneActivity.class);
		i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		startActivity(i);
		finish();
		
	}
}
