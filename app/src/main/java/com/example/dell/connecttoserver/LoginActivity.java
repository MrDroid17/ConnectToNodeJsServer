package com.example.dell.connecttoserver;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.connecttoserver.Retrofit.APIService;
import com.example.dell.connecttoserver.Retrofit.Client;
import com.example.dell.connecttoserver.model.Login;
import com.example.dell.connecttoserver.model.LoginResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.et_login_username)
    EditText loginUsername;

    @BindView(R.id.et_login_password)
    EditText loginPassword;

    @BindView(R.id.btn_login)
    Button btnLogin;

    @BindView(R.id.btn_register)
    Button btnRegister;

    SharedPreferences sharedPref;

    Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        //set title here
        setTitle(getString(R.string.user_login));

        btnRegister.setOnClickListener(v -> {
        Intent gotoRegisterIntent= new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(gotoRegisterIntent);
        });

        btnLogin.setOnClickListener(v -> {

            Login login = new Login(
                    loginUsername.getText().toString(),
                    loginPassword.getText().toString()
            );

            APIService apiService = Client.getClient().create(APIService.class);
            Call<LoginResponse> call = apiService.authenticateUser(login);

            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                    if(response.isSuccessful()){

                        Toast.makeText(LoginActivity.this,
                                "User logged in \n success: " + response.body().getIsSuccess()
                                        + "\n id: " + response.body().getUser().getId(),
                                Toast.LENGTH_SHORT).show();
                        String user_token = response.body().getToken();

                        //go to dashboard
                        Intent gotoDashboardIntent= new Intent(LoginActivity.this, DashBoardActivity.class);
                        gotoDashboardIntent.putExtra("token_id", user_token);
                        startActivity(gotoDashboardIntent);
                        finish();

                        // clear fields
                        loginUsername.setText("");
                        loginPassword.setText("");

                    }else{
                        Toast.makeText(LoginActivity.this, "Login Credentials is Wrong...", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "Error: "+ t, Toast.LENGTH_SHORT).show();
                }
            });

        });
    }
}
