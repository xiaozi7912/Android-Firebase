package com.xiaozi.android.firebase;

import android.app.Application;

import io.branch.referral.Branch;

/**
 * Created by user on 2017-11-28.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Branch.enableLogging();
        Branch.getAutoInstance(this);
    }
}
