package com.ilm.mydrinks.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.ilm.mydrinks.R;
import com.ilm.mydrinks.ui.welcome.WelcomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterConfirmationActivity extends AppCompatActivity {
    @BindView(R.id.layout_success)LinearLayout success;
    @BindView(R.id.layout_gagal)LinearLayout fail;
    @BindView(R.id.layout_gagal_age)LinearLayout fail_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_confirmation);
        ButterKnife.bind(this);

        Intent i = getIntent();
        String regisFlag = i.getStringExtra("regisFlag");

        if(regisFlag.equalsIgnoreCase("success")){
            success.setVisibility(View.VISIBLE);
            fail.setVisibility(View.GONE);
            fail_age.setVisibility(View.GONE);
        }if(regisFlag.equalsIgnoreCase("fail")){
            success.setVisibility(View.GONE);
            fail.setVisibility(View.VISIBLE);
            fail_age.setVisibility(View.GONE);
        }if(regisFlag.equalsIgnoreCase("age")){
            success.setVisibility(View.GONE);
            fail.setVisibility(View.GONE);
            fail_age.setVisibility(View.VISIBLE);
        }

    }

    @OnClick(R.id.btn_register_confirmation_back)
    public void backToRegister(View view) {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btn_register_confirmation_close)
    public void closeToWelcome(View view) {
        Intent i = new Intent(this, WelcomeActivity.class);
        startActivity(i);
        finish();
    }
}
