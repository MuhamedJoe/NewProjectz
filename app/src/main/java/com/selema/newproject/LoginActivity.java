package com.selema.newproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.selema.newproject.utils.BaseApiService;
import com.selema.newproject.utils.Login_Response;
import com.selema.newproject.utils.UtilsApi;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.btnRegister)
    Button btnRegister;

    ProgressDialog loading;
    Context mContext;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mContext = this;
        mApiService = UtilsApi.getAPIService(); // meng-init yang ada di package apihelper
        initComponents();
    }

    private void initComponents() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(mContext, null, "WelCome TO our App", true, false);
                requestLogin();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, RegisterActivity.class));
            }
        });
    }

    private void requestLogin() {
        final Intent i = new Intent(this, MainActivity.class);
        mApiService.loginRequest(etEmail.getText().toString(), etPassword.getText().toString())
                .enqueue(new Callback<Login_Response>(){
                    @Override
                    public void onResponse(Call<Login_Response> call, Response<Login_Response> response) {
                        if (response.isSuccessful()) {
                            loading.dismiss();

                            String jsonRESULTS = response.body().getResponseMessage();
                            if (jsonRESULTS.equals("logined successfully")) {
                                Toast.makeText(mContext, " LOGIN", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("id", etEmail.getText().toString());
                                startActivity(intent);
                            } else {
                                Toast.makeText(mContext, jsonRESULTS, Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            loading.dismiss();
                        }

                    }

                    @Override
                    public void onFailure(Call<Login_Response> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        loading.dismiss();

                    }
                });

    }
}
