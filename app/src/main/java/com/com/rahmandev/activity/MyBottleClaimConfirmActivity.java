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

public class MyBottleClaimConfirmActivity extends AppCompatActivity {
    @BindView(R.id.txt_mybottle_claim_bottle_name)TextView bottleClaim;
    @BindView(R.id.txt_name)TextView txtName;
    @BindView(R.id.txt_points)TextView txtPoint;

    private SessionManager sessionManager;
    private String bottle_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bottle_claim_confirm);
        ButterKnife.bind(this);

        sessionManager = new SessionManager(getApplicationContext());

        HashMap<String, String> user = sessionManager.getUserDetails();
        String firstName = user.get(SessionManager.KEY_FIRST_NAME);
        String lastName = user.get(SessionManager.KEY_LAST_NAME);
        String point = user.get(SessionManager.KEY_POINT_BALANCE);

        txtName.setText(firstName + " " + lastName);
        txtPoint.setText(point + " pts");

        getData();

        if (bottle_name.contains("Glenlivet")){
            bottleClaim.setText("The Glenlivet 18 750cl" + "\nClaimed!");
        }if (bottle_name.contains("Martell XO")){
            bottleClaim.setText("Martell XO 750cl" + "\nClaimed!");
        }if (bottle_name.contains("Martell Cordon Bleu")){
            bottleClaim.setText("Martell Cordon Bleu 750cl" + "\nClaimed!");
        }if (bottle_name.contains("Martell VSOP XO")){
            bottleClaim.setText("Martell VSOP XO 750cl" + "\nClaimed!");
        }if (bottle_name.contains("Chivas")){
            bottleClaim.setText("Chivas 12 750cl" + "\nClaimed!");
        }if (bottle_name.contains("Absolut Vodka")){
            bottleClaim.setText("Absolut Vodka 750cl" + "\nClaimed!");
        }
    }

    private void getData(){
        Intent i = getIntent();
        bottle_name = i.getStringExtra("bottle_name");
    }

    @OnClick(R.id.btn_mybottle_claim_confirm_close)
    public void closeToMain(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

}
