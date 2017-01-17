package com.ilm.mydrinks.ui.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ilm.mydrinks.BaseActivity;
import com.ilm.mydrinks.R;
import com.ilm.mydrinks.ui.login.LoginActivity;
import com.ilm.mydrinks.ui.main.MainActivity;
import com.ilm.mydrinks.ui.register.RegisterActivity;
import com.ilm.mydrinks.utility.SessionManager;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ERD on 10/10/2016.
 */
public class WelcomeActivity extends BaseActivity {
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.welcome);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        sessionManager = new SessionManager(getApplicationContext());

        if (sessionManager.isLoggedIn()) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }

    @OnClick(R.id.btn_welcome_register)
    public void toRegister(View view) {
        Intent i = new Intent(WelcomeActivity.this, RegisterActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.btn_welcome_login)
    public void toLogin(View view) {
        Intent i = new Intent(WelcomeActivity.this, LoginActivity.class);
        startActivity(i);
    }

}
