package com.ilm.mydrinks.ui.settings;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ilm.mydrinks.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ERD on 12/11/2016.
 */

public class SettingAboutFragment extends Fragment {
    private static final String TAG = "SettingAboutFragment";
    private String version;
    @BindView(R.id.txt_version_name)TextView vName;
    @BindView(R.id.txt_version_number)TextView vNumber;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_settings_about, container, false);
        ButterKnife.bind(this, view);

        try {
            appVersion();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        vNumber.setText(version);

        return view;
    }

    public String appVersion() throws PackageManager.NameNotFoundException {
        PackageInfo pInfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
        version = pInfo.versionName;
        return version;
    }

}
