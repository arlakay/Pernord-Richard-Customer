package com.ilm.mydrinks.ui.register;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ilm.mydrinks.R;

import butterknife.ButterKnife;

public class RegisterTOSActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_tos);
        ButterKnife.bind(this);

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
