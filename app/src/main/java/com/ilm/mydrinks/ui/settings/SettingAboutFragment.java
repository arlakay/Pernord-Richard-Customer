package com.ilm.mydrinks.ui.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ilm.mydrinks.R;

import butterknife.ButterKnife;

/**
 * Created by ERD on 12/11/2016.
 */

public class SettingAboutFragment extends Fragment {
    private static final String TAG = "HomeFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_settings_about, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

}
