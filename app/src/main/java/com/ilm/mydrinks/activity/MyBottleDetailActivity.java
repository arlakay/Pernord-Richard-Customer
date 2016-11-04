package com.ilm.mydrinks.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ilm.mydrinks.R;
import com.ilm.mydrinks.utility.SessionManager;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyBottleDetailActivity extends AppCompatActivity {
    @BindView(R.id.layout_bottle_glenlivet)LinearLayout glenlivet;
    @BindView(R.id.layout_bottle_martell_xo)LinearLayout martell_xo;
    @BindView(R.id.layout_bottle_martell_cordon_bleu)LinearLayout martell_cordon_bleu;
    @BindView(R.id.layout_bottle_martell_vsop)LinearLayout martell_vsop_xo;
    @BindView(R.id.layout_bottle_chivas)LinearLayout chivas;
    @BindView(R.id.layout_bottle_absolut_vodka)LinearLayout absolut_vodka;
    @BindView(R.id.txt_name)TextView txtName;
    @BindView(R.id.txt_points)TextView txtPoint;

    private SessionManager sessionManager;
    private String bottle_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bottle_detail);
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

            glenlivet.setVisibility(View.VISIBLE);
            martell_xo.setVisibility(View.GONE);
            martell_cordon_bleu.setVisibility(View.GONE);
            martell_vsop_xo.setVisibility(View.GONE);
            chivas.setVisibility(View.GONE);
            absolut_vodka.setVisibility(View.GONE);

        }if (bottle_name.contains("Martell XO")){

            glenlivet.setVisibility(View.GONE);
            martell_xo.setVisibility(View.VISIBLE);
            martell_cordon_bleu.setVisibility(View.GONE);
            martell_vsop_xo.setVisibility(View.GONE);
            chivas.setVisibility(View.GONE);
            absolut_vodka.setVisibility(View.GONE);

        }if (bottle_name.contains("Martell Cordon Bleu")){

            glenlivet.setVisibility(View.GONE);
            martell_xo.setVisibility(View.GONE);
            martell_cordon_bleu.setVisibility(View.VISIBLE);
            martell_vsop_xo.setVisibility(View.GONE);
            chivas.setVisibility(View.GONE);
            absolut_vodka.setVisibility(View.GONE);

        }if (bottle_name.contains("Martell VSOP XO")){

            glenlivet.setVisibility(View.GONE);
            martell_xo.setVisibility(View.GONE);
            martell_cordon_bleu.setVisibility(View.GONE);
            martell_vsop_xo.setVisibility(View.VISIBLE);
            chivas.setVisibility(View.GONE);
            absolut_vodka.setVisibility(View.GONE);

        }if (bottle_name.contains("Chivas")){

            glenlivet.setVisibility(View.GONE);
            martell_xo.setVisibility(View.GONE);
            martell_cordon_bleu.setVisibility(View.GONE);
            martell_vsop_xo.setVisibility(View.GONE);
            chivas.setVisibility(View.VISIBLE);
            absolut_vodka.setVisibility(View.GONE);

        }if (bottle_name.contains("Absolut Vodka")){

            glenlivet.setVisibility(View.GONE);
            martell_xo.setVisibility(View.GONE);
            martell_cordon_bleu.setVisibility(View.GONE);
            martell_vsop_xo.setVisibility(View.GONE);
            chivas.setVisibility(View.GONE);
            absolut_vodka.setVisibility(View.VISIBLE);

        }
    }

    private void getData(){
        Intent i = getIntent();
        bottle_name = i.getStringExtra("bottle_name");
    }

    @OnClick(R.id.btn_my_bottle_detail_claim)
    public void toClaim(View view) {
        Intent i = new Intent(this, MyBottleClaimConfirmActivity.class);
        i.putExtra("bottle_name", bottle_name);
        startActivity(i);
        finish();
    }

}
