package com.selema.newproject;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.selema.newproject.utils.BaseApiService;
import com.selema.newproject.utils.Search_response;
import com.selema.newproject.utils.UtilsApi;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResult extends AppCompatActivity {

    String name;
    String phone;
    String email;
    String current;
    String home;
    String bio;
    String ID;

    TextView Name;
    TextView Phone;
    TextView Email;
    TextView Current;
    TextView Home;
    TextView Bio;

    String currentDateandTime;
    Button SendMoney;
    Button RequestMoney;
    Button send;
    Button close;
    Dialog MyDialog;
    BaseApiService mApiService;

    EditText message_content;
    EditText amount;
    Context mcontext;
    String mydate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

        mcontext = this;

        mApiService = UtilsApi.getAPIService();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ID = extras.getString("accountOwner");
            email = extras.getString("email");
            phone = extras.getString("phone");
            name = extras.getString("name");
            current = extras.getString("Home");
            home = extras.getString("Current");
            bio = extras.getString("Bio");
        }

        Name = findViewById(R.id.name_txt);
        Home = findViewById(R.id.home_txt);
        Phone = findViewById(R.id.phone_txt);
        Current = findViewById(R.id.current_txt);
        Email = findViewById(R.id.email_txt);
        Home = findViewById(R.id.home_txt);
        Bio = findViewById(R.id.bio_txt);

        Name.setText(name);
        Home.setText(home);
        Phone.setText(phone);
        Current.setText(current);
        Bio.setText(bio);
        Email.setText(email);


        SendMoney = (Button) findViewById(R.id.send_money_btn);
        RequestMoney = (Button) findViewById(R.id.request_money_btn);


        SendMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "send money", Toast.LENGTH_LONG).show();
                MyCustomAlertDialog();
            }
        });
        RequestMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "send money", Toast.LENGTH_LONG).show();
                MyCustomAlertDialog();
            }
        });
    }

    public void MyCustomAlertDialog() {
        MyDialog = new Dialog(SearchResult.this);
        MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        MyDialog.setContentView(R.layout.request_money);
        MyDialog.setTitle("Request Money");


        send = (Button) MyDialog.findViewById(R.id.send_request_money_btn);
        close = (Button) MyDialog.findViewById(R.id.cancle_request_money_btn);
        amount = MyDialog.findViewById(R.id.money_amount_edtxt);
        message_content = MyDialog.findViewById(R.id.messahe_content_edtxt);

        send.setEnabled(true);
        close.setEnabled(true);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //Toast.makeText(getApplicationContext(), "Send Request money " + amount.getText().toString(), Toast.LENGTH_LONG).show();
                RequestMoney();
                MyDialog.cancel();
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialog.cancel();
            }
        });

        MyDialog.show();
    }

    private void RequestMoney() {
        Toast.makeText(mcontext, amount.getText().toString()+ " " + message_content.getText().toString() + " " +mydate+ " " +ID+ " " +phone, Toast.LENGTH_SHORT ).show();

        mApiService.RequestMoney(ID, phone, mydate, message_content.getText().toString(), amount.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {

                            String jsonRESULTS = response.body().toString();
                            if (jsonRESULTS.equals("message sent successfully")) {
                                Toast.makeText(mcontext, jsonRESULTS, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(mcontext, jsonRESULTS, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());

                    }
                });
    }
}





