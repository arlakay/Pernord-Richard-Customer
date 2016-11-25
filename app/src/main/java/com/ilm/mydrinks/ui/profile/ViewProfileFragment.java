package com.ilm.mydrinks.ui.profile;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ilm.mydrinks.R;
import com.ilm.mydrinks.api.RestApi;
import com.ilm.mydrinks.api.services.ApiService;
import com.ilm.mydrinks.model.Profile;
import com.ilm.mydrinks.utility.SessionManager;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ERD on 11/11/2016.
 */

public class ViewProfileFragment extends Fragment {
    @BindView(R.id.img_profile)ImageView imgProfile;
    @BindView(R.id.txt_profile_nama)TextView txtNama;
    @BindView(R.id.txt_profile_tgl_lahir)TextView txtBirthDate;
    @BindView(R.id.txt_profile_jk)TextView txtJK;
    @BindView(R.id.txt_profile_email)TextView txtEmail;
    @BindView(R.id.txt_profile_phone)TextView txtPhone;
    @BindView(R.id.txt_profile_poin)TextView txtPoin;

    private static final String TAG = "ViewProfileFragment";
    private Unbinder unbinder;
    private SessionManager sessionManager;
    private String username;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_profile_detail, container, false);
        ButterKnife.bind(this, view);

        sessionManager = new SessionManager(getActivity().getApplicationContext());

        final HashMap<String, String> user = sessionManager.getUserDetails();
        username = user.get(SessionManager.KEY_CUSTOMER_CODE);

        viewProfile(username);

        return view;
    }

    private void viewProfile(String cust_code) {
        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", "loading...");

        ApiService apiService =
                RestApi.getClient().create(ApiService.class);

        Call<Profile> call = apiService.viewProfile(cust_code);
        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile>call, Response<Profile> response) {
                dialog.dismiss();

                Log.d(TAG, "Status Code = " + response.code());
                Log.d(TAG, "Data received: " + new Gson().toJson(response.body()));

                if (response.isSuccessful() && response.code() == 200 && response.body().isStatus() && response.body().getMessages().contains("Get Data Customer")) {
                    txtNama.setText(response.body().getFirst_name() + " " + response.body().getLast_name());
                    txtBirthDate.setText(response.body().getBirth_date());
                    txtJK.setText(response.body().getGender());
                    txtEmail.setText(response.body().getEmail());
                    txtPhone.setText(response.body().getPhone_number());
                    txtPoin.setText(response.body().getLast_update_point());
                } else {
                    Toast.makeText(
                            getActivity(),
                            "Maaf Profile tidak berhasil dimuat, harap ulangi",
                            Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onFailure(Call<Profile>call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(
                        getActivity(),
                        "Maaf Profile tidak ditemukan",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

}
