package com.ilm.mydrinks.ui.bottomnavigation.mypoints;

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

public class MyPointDetailFragment extends Fragment {
    private static final String TAG = "MyPointDetailFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my_point_detail, container, false);
        ButterKnife.bind(this, view);

        return view;
    }
}
