package com.ilm.mydrinks.ui.login;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ilm.mydrinks.BaseActivity;
import com.ilm.mydrinks.R;
import com.ilm.mydrinks.ui.main.MainActivity;
import com.ilm.mydrinks.api.RestApi;
import com.ilm.mydrinks.api.services.ApiService;
import com.ilm.mydrinks.model.Registration;
import com.ilm.mydrinks.utility.SessionManager;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by E.R.D on 4/2/2016.
 */
public class LoginActivity extends BaseActivity {
    @BindView(R.id.uid)EditText et_username;
    @BindView(R.id.password)EditText et_pass;

    private static final String TAG = LoginActivity.class.getSimpleName();
    private SessionManager sessionManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        sessionManager = new SessionManager(getApplicationContext());

        if (sessionManager.isLoggedIn()) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        }

    }

    @OnClick(R.id.buttonlogin)
    public void toMainMenu(View view) {
        String username = et_username.getText().toString();
        String password = et_pass.getText().toString();

        if (username.trim().length() > 0 && password.trim().length() > 5) {
            ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
                loginAuth(username, password);
            }else{
                Toast.makeText(getApplicationContext(),
                    "No Internet Access Available!", Toast.LENGTH_LONG)
                    .show();}
        } else {
            Toast.makeText(getApplicationContext(),
                    "Please enter Username/Email and Password", Toast.LENGTH_LONG)
                    .show();
        }
    }

    private void loginAuth(String uname, String pass) {

        final ProgressDialog dialog = ProgressDialog.show(this, "", "loading...");

        ApiService apiService =
                RestApi.getClient().create(ApiService.class);

        Call<Registration> call = apiService.login(uname, pass);
        call.enqueue(new Callback<Registration>() {
            @Override
            public void onResponse(Call<Registration>call, Response<Registration> response) {
                dialog.dismiss();

                Log.d(TAG, "Status Code = " + response.code());
                Log.d(TAG, "Data received: " + new Gson().toJson(response.body()));

                if (response.code() == 200 && response.body().isStatus() && response.body().getMessages().contains("Success")) {
                    String cId = response.body().getCustomer_id();
                    String username = response.body().getCustomer_code();
                    String fName = response.body().getFirst_name();
                    String lName = response.body().getLast_name();
                    String birth = response.body().getBirth_date();
                    String email = response.body().getEmail();
                    String phone = response.body().getPhone_number();
                    String point = response.body().getPoint_balance();
                    String last_update_point = response.body().getLast_update_point();

                    sessionManager.setLogin(true);
                    sessionManager.createLoginSession(cId, username, fName, lName, birth, email, phone, point, last_update_point);

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
                    alertDialog.setTitle("Login Fail");
                    alertDialog.setMessage("Username/Email and Password didn't match");
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alertDialog.show();
                }
            }

            @Override
            public void onFailure(Call<Registration>call, Throwable t) {
                dialog.dismiss();

                AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
