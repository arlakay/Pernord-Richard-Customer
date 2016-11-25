package com.ilm.mydrinks.ui.changepassword;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ilm.mydrinks.R;
import com.ilm.mydrinks.api.RestApi;
import com.ilm.mydrinks.api.services.ApiService;
import com.ilm.mydrinks.model.Registration;
import com.ilm.mydrinks.utility.SessionManager;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ERD on 11/11/2016.
 */

public class ChangePasswordFragment extends Fragment {
    @BindView(R.id.et_old_pass)TextInputEditText etOldPass;
    @BindView(R.id.et_new_pass)TextInputEditText etNewPass;
    @BindView(R.id.et_confirm_new_pass)TextInputEditText etConfirmNewPass;
    @BindView(R.id.input_layout_old_pass)TextInputLayout inputLayoutOld;
    @BindView(R.id.input_layout_new_pass)TextInputLayout inputLayoutNew;
    @BindView(R.id.input_layout_confirm_new_pass)TextInputLayout inputLayoutConfirmNew;

    private static final String TAG = "ChangePasswordFragment";
    private Unbinder unbinder;
    private SessionManager sessionManager;
    private String emailFromSession;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_change_password, container, false);
        ButterKnife.bind(this, view);

        sessionManager = new SessionManager(getActivity().getApplicationContext());

        HashMap<String, String> user = sessionManager.getUserDetails();
        emailFromSession = user.get(SessionManager.KEY_EMAIL);
        String firstName = user.get(SessionManager.KEY_FIRST_NAME);
        String lastName = user.get(SessionManager.KEY_LAST_NAME);
        String point = user.get(SessionManager.KEY_POINT_BALANCE);

        return view;
    }

    @OnClick(R.id.btn_chg_pass_submit)
    public void submitToConfirm(View view) {
        String email = emailFromSession ;
        String old_pass = etOldPass.getText().toString();
        String new_pass = etNewPass.getText().toString();
        String confirm_new_pass = etConfirmNewPass.getText().toString();

        if (old_pass.trim().length() > 5 && new_pass.trim().length() > 5 && confirm_new_pass.trim().length() > 5) {
            ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if( new_pass.equals(confirm_new_pass) ) {
                if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
                    changePassword(email, old_pass, new_pass);
                } else {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "No Internet Access Available!", Toast.LENGTH_LONG)
                            .show();
                }
            }else {
                etNewPass.setError("Password tidak sama");
                etConfirmNewPass.setError("Password tidak sama");
            }
        } else {
            Toast.makeText(getActivity().getApplicationContext(),
                    "Please fill the field above !", Toast.LENGTH_LONG)
                    .show();
        }
    }

    private void changePassword(String email, String old_pass, String new_pass) {

        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", "loading...");

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
                    ChangePasswordConfirmationFragment changePasswordConfirmationFragment = new ChangePasswordConfirmationFragment();
                    Bundle arguments = new Bundle();
		            arguments.putString("message", "success");
                    changePasswordConfirmationFragment.setArguments(arguments);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.activityslidein, R.anim.activityslideinout, R.anim.activityslideoutpop, R.anim.activityslideout)
                            .add(R.id.root_frame, changePasswordConfirmationFragment, "changePasswordConfirmationFragment")
                            .addToBackStack("changePasswordConfirmationFragment")
                            .commit();
                } else {
                    ChangePasswordConfirmationFragment changePasswordConfirmationFragment = new ChangePasswordConfirmationFragment();
                    Bundle arguments = new Bundle();
                    arguments.putString("message", "fail");
                    changePasswordConfirmationFragment.setArguments(arguments);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.activityslidein, R.anim.activityslideinout, R.anim.activityslideoutpop, R.anim.activityslideout)
                            .add(R.id.root_frame, changePasswordConfirmationFragment, "changePasswordConfirmationFragment")
                            .addToBackStack("changePasswordConfirmationFragment")
                            .commit();
                }
            }

            @Override
            public void onFailure(Call<Registration>call, Throwable t) {
                dialog.dismiss();

                ChangePasswordConfirmationFragment changePasswordConfirmationFragment = new ChangePasswordConfirmationFragment();
                Bundle arguments = new Bundle();
                arguments.putString("message", "fail");
                changePasswordConfirmationFragment.setArguments(arguments);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.activityslidein, R.anim.activityslideinout, R.anim.activityslideoutpop, R.anim.activityslideout)
                        .add(R.id.root_frame, changePasswordConfirmationFragment, "changePasswordConfirmationFragment")
                        .addToBackStack("changePasswordConfirmationFragment")
                        .commit();

            }
        });
    }

}
