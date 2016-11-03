package com.com.rahmandev.ui.register;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.com.rahmandev.BaseActivity;
import com.com.rahmandev.R;
import com.com.rahmandev.api.RestApi;
import com.com.rahmandev.api.services.ApiService;
import com.com.rahmandev.model.Registration;
import com.google.gson.Gson;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by E.R.D on 4/2/2016.
 */
public class RegisterActivity extends BaseActivity {
    @BindView(R.id.input_layout_first_name)TextInputLayout inputLayoutFirstName;
    @BindView(R.id.input_layout_last_name)TextInputLayout inputLayoutLastName;
    @BindView(R.id.input_layout_username)TextInputLayout inputLayoutUsername;
    @BindView(R.id.input_layout_email)TextInputLayout inputLayoutEmail;
    @BindView(R.id.input_layout_password)TextInputLayout inputLayoutPassword;
    @BindView(R.id.input_layout_phone)TextInputLayout inputLayoutPhone;
    @BindView(R.id.input_layout_dd)TextInputLayout inputLayoutDD;
    @BindView(R.id.input_layout_mm)TextInputLayout inputLayoutMM;
    @BindView(R.id.input_layout_yy)TextInputLayout inputLayoutYY;

    @BindView(R.id.et_first_name)EditText et_first;
    @BindView(R.id.et_last_name)EditText et_last;
    @BindView(R.id.et_username)EditText et_username;
    @BindView(R.id.et_email)EditText et_email;
    @BindView(R.id.et_password)EditText et_pass;
    @BindView(R.id.et_phoneNumber)EditText et_phone;
    @BindView(R.id.et_dd)EditText et_birth_dd;
    @BindView(R.id.et_mm)EditText et_birth_mm;
    @BindView(R.id.et_yy)EditText et_birth_yyyy;

    private static final String TAG = RegisterActivity.class.getName();
    private String firstName, lastName, username, email, password, phone, birthdate, dd, mm, yy;
    private int year_now, year_allow;
    private int year_regis = 2017;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        Calendar calendar = Calendar.getInstance();
        year_now = calendar.get(Calendar.YEAR);
        year_allow = year_now - 18;

        Log.e(TAG, "now :" + year_now);
        Log.e(TAG, "allow :" + year_allow);

        et_first.addTextChangedListener(new MyTextWatcher(et_first));
        et_last.addTextChangedListener(new MyTextWatcher(et_last));
        et_username.addTextChangedListener(new MyTextWatcher(et_username));
        et_email.addTextChangedListener(new MyTextWatcher(et_email));
        et_pass.addTextChangedListener(new MyTextWatcher(et_pass));
        et_phone.addTextChangedListener(new MyTextWatcher(et_phone));
        et_birth_dd.addTextChangedListener(new MyTextWatcher(et_birth_dd));
        et_birth_mm.addTextChangedListener(new MyTextWatcher(et_birth_mm));
        et_birth_yyyy.addTextChangedListener(new MyTextWatcher(et_birth_yyyy));

    }

    @OnClick(R.id.buttonRegister)
    public void prosesSubmit(View view) {
        firstName = et_first.getText().toString();
        lastName = et_last.getText().toString();
        username = et_username.getText().toString();
        email = et_email.getText().toString();
        password = et_pass.getText().toString() ;
        phone = et_phone.getText().toString();
        dd = et_birth_dd.getText().toString();
        mm = et_birth_mm.getText().toString();
        yy = et_birth_yyyy.getText().toString();
        birthdate = yy + "-" + mm + "-" + dd;

        if (firstName.trim().length() > 0 && lastName.trim().length() > 0 && username.trim().length() > 0
                && email.trim().length() > 0 && password.trim().length() > 5 && phone.trim().length() > 0
                && dd.trim().length() > 1 && mm.trim().length() > 1 && yy.trim().length() > 3) {
            ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
                year_regis = Integer.parseInt(yy);
                if(year_regis <= year_allow) {
                    registration(firstName, lastName, username, email, password, phone, birthdate);
                }else{
                    Intent i = new Intent(this, RegisterConfirmationActivity.class);
                    i.putExtra("regisFlag", "age");
                    startActivity(i);
                    finish();
                }
            }else {
                Toast.makeText(getApplicationContext(),
                        "No Internet Access Available!", Toast.LENGTH_LONG)
                        .show();
            }
        } else {
            Toast.makeText(getApplicationContext(),
                    "Please fill field above", Toast.LENGTH_LONG)
                    .show();
        }
    }

    public boolean isEmailValid(String email) {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if(matcher.matches())
            return true;
        else
            return false;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isPhoneNumberValid(String phoneNumber) {
        int intIndex = phoneNumber.indexOf("08");
        return intIndex == 0 && phoneNumber.trim().length() > 9;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private boolean validateFirstName() {
        if (et_first.getText().toString().trim().isEmpty()) {
//            inputLayoutFirstName.setError(getString(R.string.err_msg_first_name));
            et_first.setError(getString(R.string.err_msg_first_name));
            requestFocus(et_first);
            return false;
        } else {
            inputLayoutFirstName.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateLastName() {
        if (et_last.getText().toString().trim().isEmpty()) {
            //inputLayoutLastName.setError(getString(R.string.err_msg_card_number));
            et_last.setError(getString(R.string.err_msg_last_name));
            requestFocus(et_last);
            return false;
        } else {
            inputLayoutLastName.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateUsername() {
        if (et_username.getText().toString().trim().isEmpty()) {
            //inputLayoutUsername.setError(getString(R.string.err_msg_card_number));
            et_username.setError(getString(R.string.err_msg_username));
            requestFocus(et_username);
            return false;
        } else {
            inputLayoutUsername.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateEmail() {
        String email = et_email.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email) || !isEmailValid(email)) {
//            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            et_email.setError(getString(R.string.err_msg_email));
            requestFocus(et_email);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePassword() {
        String newPassword  = et_pass.getText().toString().trim();

        if (newPassword.isEmpty() || newPassword.length()<6) {
//            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            et_pass.setError(getString(R.string.err_msg_password));
            requestFocus(et_pass);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePhone() {
        String phoneNumber = et_phone.getText().toString().trim();

        if (phoneNumber.isEmpty() || !isPhoneNumberValid(phoneNumber)) {
//            inputLayoutPhone.setError(getString(R.string.err_msg_phone_number));
            et_phone.setError(getString(R.string.err_msg_phone_number));
            requestFocus(et_phone);
            return false;
        } else {
            inputLayoutPhone.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateDD() {
        String newPassword  = et_birth_dd.getText().toString().trim();

        if (newPassword.isEmpty() || newPassword.length()<2) {
//            inputLayoutDD.setError(getString(R.string.err_msg_password));
            et_birth_dd.setError(getString(R.string.err_msg_password));
            requestFocus(et_birth_dd);
            return false;
        } else {
            inputLayoutDD.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateMM() {
        String newPassword  = et_birth_mm.getText().toString().trim();

        if (newPassword.isEmpty() || newPassword.length()<2) {
//            inputLayoutMM.setError(getString(R.string.err_msg_password));
            et_birth_mm.setError(getString(R.string.err_msg_password));
            requestFocus(et_birth_mm);
            return false;
        } else {
            inputLayoutMM.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateYYYY() {
        String newPassword  = et_birth_yyyy.getText().toString().trim();

        if (newPassword.isEmpty() || newPassword.length()<4) {
//            inputLayoutYY.setError(getString(R.string.err_msg_password));
            et_birth_yyyy.setError(getString(R.string.err_msg_password));
            requestFocus(et_birth_yyyy);
            return false;
        } else {
            inputLayoutYY.setErrorEnabled(false);
        }
        return true;
    }

    private void registration(String first_name, String last_name, String username, String email,
                              String pass, String phone, String birth_date) {

        if (!validateFirstName()) {
            return;
        }if (!validateLastName()) {
            return;
        }if (!validateUsername()) {
            return;
        }if (!validateEmail()) {
            return;
        }if (!validatePassword()) {
            return;
        }if (!validatePhone()) {
            return;
        }if (!validateDD()) {
            return;
        }if (!validateMM()) {
            return;
        }if (!validateYYYY()) {
            return;
        }

        final ProgressDialog dialog = ProgressDialog.show(this, "", "loading...");

        ApiService apiService =
                RestApi.getClient().create(ApiService.class);

        Call<Registration> call = apiService.register(first_name, last_name, username, email, pass, phone, birth_date);
        call.enqueue(new Callback<Registration>() {
            @Override
            public void onResponse(Call<Registration>call, Response<Registration> response) {
                dialog.dismiss();

                Log.d(TAG, "Status Code = " + response.code());
                Log.d(TAG, "Data received: " + new Gson().toJson(response.body()));

                if (response.code() == 200 && response.body().isStatus() == true && response.body().getMessage().contains("succes")) {
//                    String fName = response.body().getFirst_name();
//                    String lName = response.body().getLast_name();

//                    sessionManager.setLogin(true);
//                    sessionManager.createLoginSession(attId, tCode, fName, lName, passw);

                    Intent intent = new Intent(RegisterActivity.this, RegisterConfirmationActivity.class);
                    intent.putExtra("regisFlag", "success");
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(RegisterActivity.this, RegisterConfirmationActivity.class);
                    intent.putExtra("regisFlag", "fail");
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Registration>call, Throwable t) {
                dialog.dismiss();

                Intent intent = new Intent(RegisterActivity.this, RegisterConfirmationActivity.class);
                intent.putExtra("regisFlag", "fail");
                startActivity(intent);
                finish();

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.et_first_name:
                    validateFirstName();
                    break;
                case R.id.et_last_name:
                    validateLastName();
                    break;
                case R.id.et_username:
                    validateUsername();
                    break;
                case R.id.et_email:
                    validateEmail();
                    break;
                case R.id.et_password:
                    validatePassword();
                    break;
                case R.id.et_phoneNumber:
                    validatePhone();
                    break;
                case R.id.et_dd:
                    validateDD();
                    break;
                case R.id.et_mm:
                    validateMM();
                    break;
                case R.id.et_yy:
                    validateYYYY();
                    break;
            }
        }
    }

}