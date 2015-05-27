package com.androidatc.accountmanager;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.GooglePlayServicesAvailabilityException;
import com.google.android.gms.auth.UserRecoverableAuthException;

import java.io.IOException;

/**
 * Created by jorge on 20/05/2015.
 */
public class GetNameInForeground extends AbstractGetNameTask {

    public GetNameInForeground(SplashActivity mActivity, String mScope, String mEmail) {
        super(mActivity, mScope, mEmail);
    }

    @Override
    protected String fetchToken() throws IOException {
        try {
            return GoogleAuthUtil.getToken(mActivity, mEmail, mScope);
        } catch (GooglePlayServicesAvailabilityException e) {
            e.printStackTrace();
        } catch (UserRecoverableAuthException e) {
            mActivity.startActivityForResult(e.getIntent(), mRequestCode);
        } catch (GoogleAuthException e) {
            onError("Error fatal " + e.getMessage(), e);
        }
        return null;
    }
}
