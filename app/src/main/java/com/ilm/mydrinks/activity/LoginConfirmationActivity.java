package com.ilm.mydrinks.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ilm.mydrinks.R;
import com.ilm.mydrinks.ui.welcome.WelcomeActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_confirmation);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_login_confirmation_close)
    public void closeToWelcome(View view) {
        Intent i = new Intent(this, WelcomeActivity.class);
        startActivity(i);
        finish();
    }

}
