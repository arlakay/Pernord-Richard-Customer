package com.ilm.mydrinks.ui.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ilm.mydrinks.R;
import com.ilm.mydrinks.utility.SessionManager;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by ERD on 11/11/2016.
 */

public class SettingFragment extends Fragment {
    private static final String TAG = "SettingFragment";
    private Unbinder unbinder;
    private SessionManager sessionManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_settings, container, false);
        ButterKnife.bind(this, view);

        sessionManager = new SessionManager(getActivity().getApplicationContext());

        return view;
    }

    @OnClick(R.id.btn_setting_about)
    public void about(View view) {
        SettingAboutFragment settingAboutFragment = new SettingAboutFragment();
        Bundle arguments = new Bundle();
//		arguments.putString("phone", phoneStore);
        settingAboutFragment.setArguments(arguments);
        getActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.activityslidein, R.anim.activityslideinout, R.anim.activityslideoutpop, R.anim.activityslideout)
                .replace(R.id.root_frame, settingAboutFragment, "settingAboutFragment")
                .addToBackStack("settingAboutFragment")
                .commit();
    }

}
