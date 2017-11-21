package com.xiaozi.android.firebase;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.xiaozi.framework.libs.BaseActivity;
import com.xiaozi.framework.libs.utils.Logger;

public class MainActivity extends BaseActivity {
    private TextView mClinicIdTextView = null;

    private String mClinicId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        initView();

        FirebaseDynamicLinks.getInstance().getDynamicLink(getIntent())
                .addOnSuccessListener(new OnSuccessListener<PendingDynamicLinkData>() {
                    @Override
                    public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                        Logger.i(LOG_TAG, "getDynamicLink onSuccess");
                        Uri deepLink = null;
                        if (pendingDynamicLinkData != null) {
                            deepLink = pendingDynamicLinkData.getLink();
                            mClinicId = deepLink.getQueryParameter("clinic_id");
                            mClinicIdTextView.setText(mClinicId);
                        }
                        Logger.d(LOG_TAG, "getDynamicLink onSuccess deepLink : " + deepLink);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Logger.i(LOG_TAG, "getDynamicLink onFailure");
                    }
                });
    }

    @Override
    protected void initialize() {
        super.initialize();
        Logger.init(BuildConfig.DEBUG);
    }

    @Override
    protected void initView() {
        super.initView();
        Logger.i(LOG_TAG, "initView");
        mClinicIdTextView = findViewById(R.id.main_clinic_id_text);

        mClinicIdTextView.setText("");
    }
}
