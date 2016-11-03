package com.com.rahmandev.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.com.rahmandev.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsAboutActivity extends AppCompatActivity {
    @BindView(R.id.txt_versioning)TextView versioning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_about);
        ButterKnife.bind(this);

        PackageInfo pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = pInfo.versionName;

        versioning.setText(version);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
