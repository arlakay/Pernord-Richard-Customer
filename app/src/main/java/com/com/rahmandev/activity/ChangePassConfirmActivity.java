package com.com.rahmandev.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.com.rahmandev.R;
import com.com.rahmandev.ui.main.MainActivity;
import com.com.rahmandev.utility.SessionManager;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePassConfirmActivity extends AppCompatActivity {
    @BindView(R.id.txt_message_gagal)TextView txtGagal;
    @BindView(R.id.txt_message_sukses)TextView txtSukses;
    @BindView(R.id.txt_name)TextView txtName;
    @BindView(R.id.txt_points)TextView txtPoint;

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass_confirm);
        ButterKnife.bind(this);

        sessionManager = new SessionManager(getApplicationContext());

        HashMap<String, String> user = sessionManager.getUserDetails();
        String firstName = user.get(SessionManager.KEY_FIRST_NAME);
        String lastName = user.get(SessionManager.KEY_LAST_NAME);
        String point = user.get(SessionManager.KEY_POINT_BALANCE);

        txtName.setText(firstName + " " + lastName);
        txtPoint.setText(point + " pts");

        Intent i = getIntent();
        String message = i.getStringExtra("message");

        if (message.equalsIgnoreCase("success")){
            txtSukses.setVisibility(View.VISIBLE);
            txtGagal.setVisibility(View.GONE);
        } else {
            txtSukses.setVisibility(View.GONE);
            txtGagal.setVisibility(View.VISIBLE);
        }

    }

    @OnClick(R.id.btn_chg_pass_confirm_close)
    public void closeToMain(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

}
