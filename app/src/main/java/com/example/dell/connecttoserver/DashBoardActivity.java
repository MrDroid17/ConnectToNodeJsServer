package com.example.dell.connecttoserver;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.connecttoserver.Retrofit.APIService;
import com.example.dell.connecttoserver.Retrofit.Client;
import com.example.dell.connecttoserver.model.LoginResponse;
import com.example.dell.connecttoserver.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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

    @BindView(R.id.ll_dashboard)
    LinearLayout llDashboard;

    @BindView(R.id.btn_logout)
    Button btnLogout;

    @BindView(R.id.btn_show_hide_profile)
    Button btnShowHideProfile;

    private Context mContext;

    private SharedPreferences mSharedPref;

    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        ButterKnife.bind(this);

        String user_token = getIntent().getStringExtra("token_id");

       //set Activity title
        setTitle(getString(R.string.user_dashboard));

        btnShowHideProfile.setOnClickListener(v -> {

            APIService apiService = Client.getClient().create(APIService.class);
            Call<LoginResponse> call = apiService.getProfile(user_token);

            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if(response.isSuccessful()){

                        if(btnShowHideProfile.getText().equals(getString(R.string.btn_show_profile))){
                            //show profile
                            tvName.setText(response.body().getUser().getName());
                            tvUsername.setText(response.body().getUser().getUsername());
                            tvEmail.setText(response.body().getUser().getEmail());
                            btnShowHideProfile.setText(R.string.btn_hide_profile);
                            llDashboard.setVisibility(View.VISIBLE);
                        }else{
                            //hide profile
                            tvName.setText("");
                            tvUsername.setText("");
                            tvEmail.setText("");
                            btnShowHideProfile.setText(R.string.btn_show_profile);
                            llDashboard.setVisibility(View.GONE);
                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(DashBoardActivity.this, "Error: " +t ,
                            Toast.LENGTH_SHORT).show();
                }
            });
        });

        btnLogout.setOnClickListener(v -> {
            /***
             * remove user_token from shared preference on logout and make a toast appear
             * to show delete msg
             */
            SharedPreferences preferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
            preferences.edit().remove("token").commit();
            Toast.makeText(this, "token deleted Successfully \n" + user_token, Toast.LENGTH_SHORT).show();

            /***
             * go to login page on logout
             */
            Intent gotoLoginIntent= new Intent(DashBoardActivity.this, LoginActivity.class);
            startActivity(gotoLoginIntent);
            finish();
        });
    }
}
