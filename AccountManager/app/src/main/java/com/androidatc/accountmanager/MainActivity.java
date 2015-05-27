package com.androidatc.accountmanager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;


public class MainActivity extends ActionBarActivity {
  private ImageView imageProfile;
    TextView name, email, gender;
    String textName, textEmail, textGender, textBirthday, userImageUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageProfile= (ImageView) findViewById(R.id.imageView);
        name = (TextView) findViewById(R.id.textViewNameValue);
        email = (TextView) findViewById(R.id.textViewEmailValue);
        gender = (TextView) findViewById(R.id.textViewGenderValue);

        Intent i = new Intent();
        textEmail = i.getStringExtra("email_id");
        email.setText(textEmail);
        try{
            JSONObject profileData = new JSONObject(AbstractGetNameTask.GOOGLE_USER_DATA);
            if (profileData.has("picture")){
                userImageUrl = profileData.getString("picture");

            }
        }catch (JSONException e){

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Created by jorge on 20/05/2015.
     */
    public static class GetImageFormUrl extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... params) {
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
        }
        private Bitmap downloadImage(String url){
            Bitmap bitmap = null;
            InputStream stream = null;
            BitmapFactory.Options bmoptions = new BitmapFactory.Options();
            bmoptions.inSampleSize = 1;
            try {
                stream = getHttpConnection(url);

               }catch(Exception e){

            }


            return bitmap;
        }

        private InputStream getHttpConnection(String url) {
            return null;
        }

    }
}
