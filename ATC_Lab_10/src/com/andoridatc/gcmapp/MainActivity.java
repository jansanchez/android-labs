/**
 * Copyright © 2014 Android ATC.
 * 
 * Author: Android ATC Training Team.
 * 
 * Source code in this project is provided for trainers of Android ATC 
 * course AND-401 titled "Android Application Development".
 * 
 * The is the source code for Lab 10: Creating a Push Notification Application.
 * 
 */
package com.andoridatc.gcmapp;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

import com.androidatc.gcmapp.R;
import com.google.android.gcm.GCMRegistrar;

public class MainActivity extends ActionBarActivity {

	private final String SENDER_ID = "914824554654-this-is-google-project-id";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		//Register app with GCM to make ready to receive push notifications.
		GCMRegistrar.checkDevice(this);
		GCMRegistrar.checkManifest(this);
		GCMRegistrar.register(this, SENDER_ID);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
