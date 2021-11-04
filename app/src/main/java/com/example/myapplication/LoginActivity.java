package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    Button login;
    EditText edtusername, edtpassword;
    TextView back_to_register, forgot_password;
    DB_Helper_UserTable DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DB = new DB_Helper_UserTable(this);
        edtpassword = (EditText) findViewById(R.id.password_login);
        edtusername = (EditText) findViewById(R.id.username_login);
        login = (Button) findViewById(R.id.login_button);
        back_to_register = (TextView) findViewById(R.id.txt_signup);
        forgot_password = (TextView) findViewById(R.id.forgotPassword);

        back_to_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtusername.getText().toString();
                String password = edtpassword.getText().toString();

                if (username.equals("") || password.equals(""))
                    Toast.makeText(LoginActivity.this, "All fields are required!", Toast.LENGTH_SHORT).show();
                else {
                    Boolean check_username_and_password = DB.check_username_and_password(username, password);
                    if (check_username_and_password == true) {
                        Toast.makeText(LoginActivity.this, "Successfuly logged in!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}