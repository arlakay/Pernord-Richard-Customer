package com.ilm.mydrinks.ui.bottomnavigation.mybottles;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.ilm.mydrinks.R;
import com.ilm.mydrinks.api.RestApi;
import com.ilm.mydrinks.api.services.ApiService;
import com.ilm.mydrinks.model.Claim;
import com.ilm.mydrinks.utility.SessionManager;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ERD on 12/11/2016.
 */

public class MyBottleDetailFragment extends Fragment {
    @BindView(R.id.layout_bottle_glenlivet)LinearLayout glenlivet;
    @BindView(R.id.layout_bottle_martell_xo)LinearLayout martell_xo;
    @BindView(R.id.layout_bottle_martell_cordon_bleu)LinearLayout martell_cordon_bleu;
    @BindView(R.id.layout_bottle_martell_vsop)LinearLayout martell_vsop_xo;
    @BindView(R.id.layout_bottle_chivas)LinearLayout chivas;
    @BindView(R.id.layout_bottle_absolut_vodka)LinearLayout absolut_vodka;

    private static final String TAG = "MyBottleDetailFragment";
    private SessionManager sessionManager;
    private String username;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my_bottle_detail, container, false);
        ButterKnife.bind(this, view);

        sessionManager = new SessionManager(getActivity().getApplicationContext());

        HashMap<String, String> user = sessionManager.getUserDetails();
        username = user.get(SessionManager.KEY_CUSTOMER_CODE);

        String bottle_name    = getArguments().getString("productName");

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

        return view;
    }

    @OnClick(R.id.btn_my_bottle_detail_claim)
    public void claim(View view) {
        IntentIntegrator.forSupportFragment(this).setPrompt("Place a barcode inside the viewfinder rectangle to scan it").initiateScan();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Log.d("MainActivity", "Cancelled scan");
                Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Log.d("MainActivity", "Scanned");
//                Toast.makeText(getActivity(), "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                claimBottle(username, result.getContents());
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void claimBottle(String cust_code, String serial_number) {
        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", "loading...");

        ApiService apiService =
                RestApi.getClient().create(ApiService.class);

        Call<Claim> call = apiService.claimBottle(serial_number, cust_code);
        call.enqueue(new Callback<Claim>() {
            @Override
            public void onResponse(Call<Claim>call, Response<Claim> response) {
                dialog.dismiss();

                Log.d(TAG, "Status Code = " + response.code());
                Log.d(TAG, "Data received: " + new Gson().toJson(response.body()));

                if (response.isSuccessful() && response.code() == 200 && response.body().isStatus() && response.body().getMessages().contains("Claim Bottle Success")) {
                    MyBottleConfirmFragment myBottleConfirmFragment = new MyBottleConfirmFragment();
                    Bundle arguments = new Bundle();
                    arguments.putString("message", "success");
                    myBottleConfirmFragment.setArguments(arguments);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.activityslidein, R.anim.activityslideinout, R.anim.activityslideoutpop, R.anim.activityslideout)
                            .add(R.id.root_frame, myBottleConfirmFragment, "myBottleConfirmFragment")
                            .addToBackStack("myBottleConfirmFragment")
                            .commit();
                } else {
                    Toast.makeText(
                            getActivity(),
                            "QR Code tidak valid, Ulangi proses scan",
                            Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onFailure(Call<Claim>call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(
                        getActivity(),
                        "QR Code tidak dapat didefinisikan",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

}
