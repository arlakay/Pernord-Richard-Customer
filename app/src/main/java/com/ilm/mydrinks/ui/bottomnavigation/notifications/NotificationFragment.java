package com.ilm.mydrinks.ui.bottomnavigation.notifications;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ilm.mydrinks.R;

/**
 * Created by ERD on 11/11/2016.
 */

public class NotificationFragment extends Fragment {

    private static final String TAG = "NotificationsFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        return view;
    }

}
