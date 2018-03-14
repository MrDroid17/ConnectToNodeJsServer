package com.example.dell.connecttoserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.tv_login_username)
    TextView loginUsername;

    @BindView(R.id.tv_login_password)
    TextView loginPassword;

    @BindView(R.id.btn_login)
    TextView btnLogin;

    @BindView(R.id.btn_register)
    TextView btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
    }
}
