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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FortuneActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fortune);

		MyPreferences pref = new MyPreferences(FortuneActivity.this);

		if (pref.isFirstTime()) {
			Toast.makeText(FortuneActivity.this, "Hi " + pref.getUserName(),
					Toast.LENGTH_LONG).show();
			pref.setOld(true);
		} else
			Toast.makeText(FortuneActivity.this,
					"Welcome back " + pref.getUserName(), Toast.LENGTH_LONG)
					.show();
		
		new GetDailyFortune().execute((Void)null);

	}

	public class GetDailyFortune extends AsyncTask<Void, Void, Boolean> {

		private TextView fortuneText;
		String jsonResponse;
		
		@Override
		protected void onPreExecute() {
			fortuneText = (TextView) findViewById(R.id.fortune);
			fortuneText.setText("Loading...");
		}

		@Override
		protected Boolean doInBackground(Void... params) {

			ConnectionDetector cd = new ConnectionDetector(
					getApplicationContext());
			if (cd.isConnectingToInternet()) {
				try {
					DefaultHttpClient httpClient = new DefaultHttpClient();
					HttpGet httpGet = new HttpGet(
							"http://www.iheartquotes.com/api/v1/random.json");

					HttpResponse httpResponse = httpClient.execute(httpGet);
					HttpEntity httpEntity = httpResponse.getEntity();
					InputStream is = httpEntity.getContent();

					BufferedReader reader = new BufferedReader(
							new InputStreamReader(is, "iso-8859-1"), 8);
					StringBuilder sb = new StringBuilder();
					String line = null;
					while ((line = reader.readLine()) != null) {
						sb.append(line + "\n");
					}
					is.close();
					jsonResponse = sb.toString();
					writeToFile(jsonResponse);

				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					return false;
				} catch (ClientProtocolException e) {
					e.printStackTrace();
					return false;
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}

				return true;
			} else
				return false;
		}

		@Override
		protected void onPostExecute(final Boolean success) {

			JSONObject obj;
			try {
				if (success) {
					obj = new JSONObject(jsonResponse);

				} else {
					obj = new JSONObject(readFromFile());
				}

				String fortune = obj.getString("quote");
				fortuneText.setText(fortune);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		private void writeToFile(String data) {
			try {
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
						openFileOutput("Fortune.json", Context.MODE_PRIVATE));
				outputStreamWriter.write(data);
				outputStreamWriter.close();
			} catch (IOException e) {
				Log.e("Message:", "File write failed: " + e.toString());
			}

		}

		private String readFromFile() {

			String ret = " ";

			try {
				InputStream inputStream = openFileInput("Fortune.json");

				if (inputStream != null) {
					InputStreamReader inputStreamReader = new InputStreamReader(
							inputStream);
					BufferedReader bufferedReader = new BufferedReader(
							inputStreamReader);
					String receiveString = "";
					StringBuilder stringBuilder = new StringBuilder();
					Log.v("Message:", "reading...");
					while ((receiveString = bufferedReader.readLine()) != null) {
						stringBuilder.append(receiveString);
					}

					inputStream.close();
					ret = stringBuilder.toString();
				}
			} catch (FileNotFoundException e) {
				Log.e("Message:", "File not found: " + e.toString());
			} catch (IOException e) {
				Log.e("Message:", "Can not read file: " + e.toString());
			}

			return ret;
		}

	}

}
