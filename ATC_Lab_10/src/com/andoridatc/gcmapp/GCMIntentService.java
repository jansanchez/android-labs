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

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.androidatc.gcmapp.R;
import com.google.android.gcm.GCMBaseIntentService;

public class GCMIntentService extends GCMBaseIntentService {

	@Override
	protected void onError(Context arg0, String arg1) {
		
	}

	@Override
	protected void onMessage(Context context, Intent arg1) {
		int icon = R.drawable.ic_launcher;
		long when = System.currentTimeMillis();
		String message = "Hello Android ATC!";
		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		
		Notification notification = new Notification(icon, message, when);

		String title = context.getString(R.string.app_name);

		Intent notificationIntent = new Intent(context, MainActivity.class);
		// set intent so it does not start a new activity
		notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_SINGLE_TOP);
		PendingIntent intent = PendingIntent.getActivity(context, 0,
				notificationIntent, 0);
		notification.setLatestEventInfo(context, title, message, intent);
		notification.flags |= Notification.FLAG_AUTO_CANCEL;

		// Play default notification sound
		notification.defaults |= Notification.DEFAULT_SOUND;

		// Vibrate if vibrate is enabled
		notification.defaults |= Notification.DEFAULT_VIBRATE;
		notificationManager.notify(0, notification);

	}

	@Override
	protected void onRegistered(Context arg0, String arg1) {
		
	}

	@Override
	protected void onUnregistered(Context arg0, String arg1) {
		
	}

}
