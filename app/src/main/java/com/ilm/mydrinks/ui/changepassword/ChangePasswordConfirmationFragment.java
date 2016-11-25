package com.ilm.mydrinks.ui.changepassword;

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
 * Created by ERD on 14/11/2016.
 */

public class ChangePasswordConfirmationFragment extends Fragment {
    @BindView(R.id.txt_message_gagal)TextView txtGagal;
    @BindView(R.id.txt_message_sukses)TextView txtSukses;

    private static final String TAG = "ChangePasswordConfirmationFragment";
    private String message;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_change_pass_confirm, container, false);
        ButterKnife.bind(this, view);

        message = getArguments().getString("message");

        if (message.equalsIgnoreCase("success")){
            txtSukses.setVisibility(View.VISIBLE);
            txtGagal.setVisibility(View.GONE);
        } else {
            txtSukses.setVisibility(View.GONE);
            txtGagal.setVisibility(View.VISIBLE);
        }

        return view;
    }
}
