package com.xiaozi.android.firebase;


import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by user on 2017-08-23.
 */

public class SuperInstanceIDService extends FirebaseInstanceIdService {
    private final String LOG_TAG = getClass().getSimpleName();

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
    }
}
