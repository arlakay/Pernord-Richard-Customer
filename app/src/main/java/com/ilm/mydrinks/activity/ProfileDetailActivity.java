package com.ilm.mydrinks.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.ilm.mydrinks.R;
import com.ilm.mydrinks.ui.main.MainActivity;
import com.ilm.mydrinks.utility.SessionManager;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileDetailActivity extends AppCompatActivity {
    @BindView(R.id.txt_name)TextView txtName;
    @BindView(R.id.txt_points)TextView txtPoint;

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_detail);
        ButterKnife.bind(this);

        sessionManager = new SessionManager(getApplicationContext());

        HashMap<String, String> user = sessionManager.getUserDetails();
        String firstName = user.get(SessionManager.KEY_FIRST_NAME);
        String lastName = user.get(SessionManager.KEY_LAST_NAME);
        String point = user.get(SessionManager.KEY_POINT_BALANCE);

        txtName.setText(firstName + " " + lastName);
        txtPoint.setText(point + " pts");

    }

    @OnClick(R.id.btn_profile_close)
    public void closeToMain(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

}
