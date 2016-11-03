package com.com.rahmandev.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.com.rahmandev.R;
import com.com.rahmandev.api.RestApi;
import com.com.rahmandev.api.services.ApiService;
import com.com.rahmandev.model.Registration;
import com.com.rahmandev.utility.SessionManager;
import com.google.gson.Gson;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {
    @BindView(R.id.et_old_pass)TextInputEditText etOldPass;
    @BindView(R.id.et_new_pass)TextInputEditText etNewPass;
    @BindView(R.id.et_confirm_new_pass)TextInputEditText etConfirmNewPass;
    @BindView(R.id.input_layout_old_pass)TextInputLayout inputLayoutOld;
    @BindView(R.id.input_layout_new_pass)TextInputLayout inputLayoutNew;
    @BindView(R.id.input_layout_confirm_new_pass)TextInputLayout inputLayoutConfirmNew;
    @BindView(R.id.txt_name)TextView txtName;
    @BindView(R.id.txt_points)TextView txtPoint;

    private static final String TAG = ChangePasswordActivity.class.getSimpleName();
    private SessionManager sessionManager;
    private String emailFromSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);

        sessionManager = new SessionManager(getApplicationContext());

        HashMap<String, String> user = sessionManager.getUserDetails();
        emailFromSession = user.get(SessionManager.KEY_EMAIL);
        String firstName = user.get(SessionManager.KEY_FIRST_NAME);
        String lastName = user.get(SessionManager.KEY_LAST_NAME);
        String point = user.get(SessionManager.KEY_POINT_BALANCE);

        txtName.setText(firstName + " " + lastName);
        txtPoint.setText(point + " pts");
    }

    @OnClick(R.id.btn_chg_pass_submit)
    public void submitToConfirm(View view) {
        String email = emailFromSession ;
        String old_pass = etOldPass.getText().toString();
        String new_pass = etNewPass.getText().toString();
        String confirm_new_pass = etConfirmNewPass.getText().toString();

        if (old_pass.trim().length() > 5 && new_pass.trim().length() > 5 && confirm_new_pass.trim().length() > 5) {
            ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if( new_pass.equals(confirm_new_pass) ) {
                if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
                    changePassword(email, old_pass, new_pass);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "No Internet Access Available!", Toast.LENGTH_LONG)
                            .show();
                }
            }else {
                etNewPass.setError("Password tidak sama");
                etConfirmNewPass.setError("Password tidak sama");
            }
        } else {
            Toast.makeText(getApplicationContext(),
                    "Please fill the field above !", Toast.LENGTH_LONG)
                    .show();
        }

    }

    private void changePassword(String email, String old_pass, String new_pass) {

        final ProgressDialog dialog = ProgressDialog.show(this, "", "loading...");

        ApiService apiService =
                RestApi.getClient().create(ApiService.class);

        Call<Registration> call = apiService.changePassword(email, old_pass, new_pass);
        call.enqueue(new Callback<Registration>() {
            @Override
            public void onResponse(Call<Registration>call, Response<Registration> response) {
                dialog.dismiss();

                Log.d(TAG, "Status Code = " + response.code());
                Log.d(TAG, "Data received: " + new Gson().toJson(response.body()));

                if (response.code() == 200 && response.body().isStatus() && response.body().getMessages().contains("success")) {
                    Intent intent = new Intent(ChangePasswordActivity.this, ChangePassConfirmActivity.class);
                    intent.putExtra("message", "success");
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(ChangePasswordActivity.this, ChangePassConfirmActivity.class);
                    intent.putExtra("message", "fail");
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Registration>call, Throwable t) {
                dialog.dismiss();

                AlertDialog alertDialog = new AlertDialog.Builder(ChangePasswordActivity.this).create();
                alertDialog.setTitle("Error");
                alertDialog.setMessage("Network Error"+t.getMessage());
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();

            }
        });
    }


}
