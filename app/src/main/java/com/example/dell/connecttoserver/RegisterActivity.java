package com.example.dell.connecttoserver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.connecttoserver.Retrofit.APIService;
import com.example.dell.connecttoserver.Retrofit.Client;
import com.example.dell.connecttoserver.model.User;
import com.example.dell.connecttoserver.model.UserApiResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.et_register_name)
    EditText registerName;

    @BindView(R.id.et_register_username)
    EditText registerUsername;

    @BindView(R.id.et_register_email)
    EditText registeremail;

    @BindView(R.id.et_register_password)
    EditText registerPassword;

    @BindView(R.id.btn_login)
    Button btnLogin;

    @BindView(R.id.btn_register)
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);

        //set register title
        setTitle("User Register");

        btnRegister.setOnClickListener(v -> {
            User user = new User(
                    registerName.getText().toString(),
                    registerUsername.getText().toString(),
                    registeremail.getText().toString(),
                    registerPassword.getText().toString()
            );
            sendRequest(user);
        });

        btnLogin.setOnClickListener(v -> {
            Intent gotoLoginIntent= new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(gotoLoginIntent);
        });
    }

    private void sendRequest(User user) {

        APIService apiService = Client.getClient().create(APIService.class);
        Call<UserApiResponse> call = apiService.registerUser(user);

        call.enqueue(new Callback<UserApiResponse>() {
            @Override
            public void onResponse(Call<UserApiResponse> call, Response<UserApiResponse> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "User Registered\n success: "
                            + response.body().isSuccess(), Toast.LENGTH_SHORT).show();

                    // clear fields
                    registerName.setText("");
                    registerUsername.setText("");
                    registeremail.setText("");
                    registerPassword.setText("");

                    Intent gotoLoginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(gotoLoginIntent);
                }
            }

            @Override
            public void onFailure(Call<UserApiResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Registration Failled... "
                        + t, Toast.LENGTH_SHORT).show();
            }
        });
    }


}
