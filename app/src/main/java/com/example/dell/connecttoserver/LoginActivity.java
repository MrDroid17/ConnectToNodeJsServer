package com.example.dell.connecttoserver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.et_login_username)
    EditText loginUsername;

    @BindView(R.id.et_login_password)
    EditText loginPassword;

    @BindView(R.id.btn_login)
    Button btnLogin;

    @BindView(R.id.btn_register)
    Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        btnRegister.setOnClickListener(v -> {
        Intent intent= new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
        });
    }
}
