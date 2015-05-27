package com.androidatc.accountmanager;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

/**
 * Created by jorge on 20/05/2015.
 */
public abstract class AbstractGetNameTask extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "TokenInfoTask";
    protected SplashActivity mActivity;
    public static String GOOGLE_USER_DATA = "No_data";
    protected String mScope;
    protected String mEmail;
    protected int mRequestCode;

    public AbstractGetNameTask(SplashActivity mActivity, String mScope, String mEmail) {
        this.mActivity = mActivity;
        this.mScope = mScope;
        this.mEmail = mEmail;
    }

    @Override
    protected Void doInBackground(Void... params) {
        return null;
    }

    protected void onError(String msg, Exception e){
        if(e != null){
            Log.e(TAG,"Exception:",e);
        }
    }
    protected abstract String fetchToken() throws IOException;

    private void fetchNameFromProfileServer() throws IOException,JSONException{
        String token = fetchToken();
        URL url = new URL("https://www.googleapis.com/oauth2/v1/userinfo?userinfo?access_token="+
        token);

    }
}
