package com.ilm.mydrinks.ui.bottomnavigation.mypoints;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.ilm.mydrinks.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ERD on 12/11/2016.
 */

public class MyPointDetailFragment extends Fragment {
    @BindView(R.id.spin_my_point_pick_outlet)Spinner spinPickOutlet;
    @BindView(R.id.et_enter_paasword)TextInputEditText pass_redeem_outlet;
    private static final String TAG = "MyPointDetailFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my_point_detail, container, false);
        ButterKnife.bind(this, view);

        setupSpinner();

        return view;
    }

    private void setupSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.outlet,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinPickOutlet.setAdapter(adapter);
//        spinPickOutlet.setOnItemSelectedListener(this);
    }

    @OnClick(R.id.btn_my_point_detail_submit)
    public void redeem_process(View view) {
        if (pass_redeem_outlet.getText().length() > 0){
            MyPointConfirmFragment myPointConfirmFragment = new MyPointConfirmFragment();
            Bundle arguments = new Bundle();
//		arguments.putString("phone", phoneStore);
            myPointConfirmFragment.setArguments(arguments);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.activityslidein, R.anim.activityslideinout, R.anim.activityslideoutpop, R.anim.activityslideout)
                    .replace(R.id.root_frame, myPointConfirmFragment, "myPointConfirmFragment")
                    .addToBackStack("myPointConfirmFragment")
                    .commit();
        }else{
            Toast.makeText(getActivity(), "Please enter your pin / password!", Toast.LENGTH_SHORT).show();
        }
    }

}
