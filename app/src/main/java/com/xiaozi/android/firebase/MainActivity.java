package com.xiaozi.android.firebase;

import android.os.Bundle;

import com.xiaozi.framework.libs.BaseActivity;
import com.xiaozi.framework.libs.utils.Logger;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        initView();
    }

    @Override
    protected void initialize() {
        super.initialize();
        Logger.init(BuildConfig.DEBUG);
    }

    @Override
    protected void initView() {
        super.initView();
    }
}
