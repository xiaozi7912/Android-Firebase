package com.xiaozi.android.firebase;


import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.xiaozi.framework.libs.utils.Logger;

/**
 * Created by user on 2017-08-23.
 */

public class SuperInstanceIDService extends FirebaseInstanceIdService {
    private final String LOG_TAG = getClass().getSimpleName();

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        Logger.i(LOG_TAG, "onTokenRefresh");
        String firebaseToken = FirebaseInstanceId.getInstance().getToken();
        Logger.d(LOG_TAG, "onTokenRefresh firebaseToken : " + firebaseToken);
    }
}
