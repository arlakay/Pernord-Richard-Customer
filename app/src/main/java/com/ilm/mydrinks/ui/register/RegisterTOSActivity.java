package com.ilm.mydrinks.ui.register;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import com.ilm.mydrinks.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterTOSActivity extends DialogFragment implements CompoundButton.OnCheckedChangeListener{
    @BindView(R.id.parent_scroll)ScrollView parentScroll;
    @BindView(R.id.child_scroll_atas)ScrollView childScrollAtas;
    @BindView(R.id.child_scroll_bawah)ScrollView childScrollBawah;
    @BindView(R.id.pop_up_close)Button btnClose;
    @BindView(R.id.check_tos)CheckBox checkToS;
    @BindView(R.id.check_pp)CheckBox checkPP;

    private EditText mEditText;
    private String mTos, mPP;

    public RegisterTOSActivity() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_tos_pp, container);
        ButterKnife.bind(this, view);

        mEditText = (EditText) view.findViewById(R.id.txt_your_name);
        getDialog().setTitle("Hello");

        parentScroll.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(false);
                return false;
            }
        });
        childScrollAtas.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        childScrollBawah.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        checkToS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    //checked
                    Toast.makeText(getActivity(), "Checked", Toast.LENGTH_LONG).show();
                    mTos = "agree";
                } else {
                    //not checked
                    Toast.makeText(getActivity(), "Not Checked", Toast.LENGTH_LONG).show();
                    mTos = "disagree";
                }
            }
        });

        checkPP.setOnCheckedChangeListener(this);

        return view;
    }

    private OnDialogCompleteListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnDialogCompleteListener) activity;
        }
        catch (final ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnCompleteListener");
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked) {
            if(compoundButton==checkToS) {
//                Toast.makeText(getActivity(), "Checked", Toast.LENGTH_LONG).show();
                mTos = "agree";
            }
            if(compoundButton==checkPP) {
//                Toast.makeText(getActivity(), "Checked", Toast.LENGTH_LONG).show();
                mPP = "agree";
            }
            this.mListener.onComplete(mTos, mPP);
        } else {
            if(compoundButton==checkToS) {
//                Toast.makeText(getActivity(), "Checked", Toast.LENGTH_LONG).show();
                mTos = "agree";
            }
            if(compoundButton==checkPP) {
//                Toast.makeText(getActivity(), "Checked", Toast.LENGTH_LONG).show();
                mTos = "agree";
            }
            this.mListener.onComplete(mTos, mPP);
        }
    }

}
