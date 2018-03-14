package com.example.dell.connecttoserver;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.connecttoserver.Retrofit.APIService;
import com.example.dell.connecttoserver.Retrofit.Client;
import com.example.dell.connecttoserver.model.LoginResponse;
import com.example.dell.connecttoserver.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashBoardActivity extends AppCompatActivity {

    @BindView(R.id.tv_user_name)
    TextView tvName;

    @BindView(R.id.tv_user_username)
    TextView tvUsername;

    @BindView(R.id.tv_user_email)
    TextView tvEmail;

    @BindView(R.id.btn_logout)
    Button btnLogout;

    @BindView(R.id.btn_show_hide_profile)
    Button btnShowHideProfile;

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        //setVisibility gone of textview by default
        tvName.setVisibility(View.GONE);
        tvUsername.setVisibility(View.GONE);
        tvEmail.setVisibility(View.GONE);

        //set string
        setTitle(getString(R.string.user_dashboard));
        ButterKnife.bind(this);

        //shared preference
        sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        btnShowHideProfile.setOnClickListener(v -> {
            String tokenFromSharedPreferences = sharedPref.getString("token", "token not found");
            APIService apiService = Client.getClient().create(APIService.class);
            Call<User> call = apiService.getProfile(tokenFromSharedPreferences);

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(response.isSuccessful()){
                        //set visibility visible
                        tvName.setVisibility(View.VISIBLE);
                        tvUsername.setVisibility(View.VISIBLE);
                        tvEmail.setVisibility(View.VISIBLE);

                        tvName.setText(response.body().getName());
                        tvUsername.setText(response.body().getUsername());
                        tvEmail.setText(response.body().getEmail());

                        btnShowHideProfile.setText(R.string.btn_hide_profile);

                    }

                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });

        });

        btnLogout.setOnClickListener(v -> {
            // remove token from shared preference on logout
            SharedPreferences.Editor  editor= sharedPref.edit();
            editor.remove("token");
            editor.commit();

            Toast.makeText(this, "token deleted", Toast.LENGTH_SHORT).show();

            //got to login page on logout
            Intent gotoLoginIntent= new Intent(DashBoardActivity.this, LoginActivity.class);
            startActivity(gotoLoginIntent);
        });
    }
}
