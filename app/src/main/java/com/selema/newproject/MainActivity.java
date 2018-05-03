package com.selema.newproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.felix.bottomnavygation.BottomNav;
import com.felix.bottomnavygation.ItemNav;
import com.selema.newproject.Messages.MyMessages;
import com.selema.newproject.QR.DecoderActivity;
import com.selema.newproject.QR.GenQr;
import com.selema.newproject.utils.BaseApiService;
import com.selema.newproject.utils.Search_response;
import com.selema.newproject.utils.UtilsApi;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    /* @BindView(R.id.search_id)
     EditText searchView;
    */
    String ID = "1";
    BaseApiService mApiService;
    SearchView searchViews;
    String myEmail;
    String myName;
    Context mContext;

    Button open_message;
    String SearchQuery;

    /* @BindView(R.id.search_btn)
     Button search_btn;
 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        searchViews = (SearchView) findViewById(R.id.searchView);
        open_message = findViewById(R.id.open_message_btn);
        Bundle extras = getIntent().getExtras();
        if (extras != null)
            ID = extras.getString("id");
        //getMyData();

        BottomNav bottomNav = findViewById(R.id.bottomNav);
        bottomNav.addItemNav(new ItemNav(this, R.mipmap.ic_launcher, "Explore").addColorAtive(R.color.colorAccent));
        bottomNav.addItemNav(new ItemNav(this, R.mipmap.ic_launcher).addColorAtive(R.color.colorAccent));
        bottomNav.build();

        bottomNav.setTabSelectedListener(listener);


        mContext = this;
        mApiService = UtilsApi.getAPIService(); // meng-init yang ada di package apihelper


        open_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent i = new Intent(mContext, MyMessages.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Search(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
               Search(newText);
                return true;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    BottomNav.OnTabSelectedListener listener = new BottomNav.OnTabSelectedListener() {
        @Override
        public void onTabSelected(int position) {
            Toast.makeText(MainActivity.this, "Click position " + position, Toast.LENGTH_SHORT).show();
            if (position == 0) {
                Intent intent = new Intent(MainActivity.this, GenQr.class);
                intent.putExtra("email", ID);
                startActivity(intent);
            } else {
                Intent intent = new Intent(MainActivity.this, DecoderActivity.class);
                startActivity(intent);

            }
        }

        @Override
        public void onTabLongSelected(int i) {

        }


    };


    private void Search(final String key) {

        mApiService.SearchRequest(key)
                .enqueue(new Callback<Search_response>() {
                    @Override
                    public void onResponse(Call<Search_response> call, Response<Search_response> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getEmail().equals(key) || response.body().getPhoneNumber().equals(key)) {
                                Toast.makeText(mContext, "we found it", Toast.LENGTH_SHORT).show();

                            } else {

                                Toast.makeText(mContext, " not match any account", Toast.LENGTH_SHORT).show();
                            }
                            Intent intent = new Intent(MainActivity.this, SearchResult.class);

                            intent.putExtra("accountOwner",ID);
                            intent.putExtra("email", response.body().getEmail().toString());
                            intent.putExtra("phone", response.body().getPhoneNumber().toString());
                            intent.putExtra("name", response.body().getFristName().toString() + response.body().getLastName().toString());
                            intent.putExtra("Home", response.body().getHomeCity().toString());
                            intent.putExtra("Current", response.body().getCurrentCity().toString());
                            intent.putExtra("Bio", response.body().getBio().toString());
                            startActivity(intent);

                        }
                    }

                    @Override
                    public void onFailure(Call<Search_response> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());

                    }
                });


    }


    private void getMyData() {

        mApiService.SearchRequest(ID)
                .enqueue(new Callback<Search_response>() {
                    @Override
                    public void onResponse(Call<Search_response> call, Response<Search_response> response) {
                        if (response.isSuccessful()) {
                            myEmail = response.body().getEmail();
                            myName = response.body().getFristName() + " " + response.body().getLastName();
                        }
                    }

                    @Override
                    public void onFailure(Call<Search_response> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());

                    }
                });


    }

}
