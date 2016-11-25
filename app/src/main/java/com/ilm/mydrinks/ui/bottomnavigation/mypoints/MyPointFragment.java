package com.ilm.mydrinks.ui.bottomnavigation.mypoints;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ilm.mydrinks.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ERD on 11/11/2016.
 */

public class MyPointFragment extends Fragment {
    private static final String TAG = "MyPointFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_points, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.btn_my_point_1)
    public void pointRedeem(View view) {
        MyPointDetailFragment myPointDetailFragment = new MyPointDetailFragment();
        Bundle arguments = new Bundle();
//		arguments.putString("phone", phoneStore);
        myPointDetailFragment.setArguments(arguments);
        getActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.activityslidein, R.anim.activityslideinout, R.anim.activityslideoutpop, R.anim.activityslideout)
                .add(R.id.root_frame, myPointDetailFragment, "myPointDetailFragment")
                .addToBackStack("myPointDetailFragment")
                .commit();
    }

}
