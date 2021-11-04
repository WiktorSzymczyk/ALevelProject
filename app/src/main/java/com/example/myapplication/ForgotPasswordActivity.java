package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class ForgotPasswordActivity extends AppCompatActivity {

    private Button resetPassword;
    private EditText edtemail, edtusername, edtpassword;
    DB_Helper_UserTable DB;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        
        DB = new DB_Helper_UserTable(this);
        edtemail = (EditText) findViewById(R.id.email_forgot);
        edtpassword = (EditText) findViewById(R.id.new_password_forgot);
        edtusername = (EditText) findViewById(R.id.username_forgot);
        resetPassword = (Button) findViewById(R.id.reset_password);
        
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtusername.getText().toString();
                String password = edtpassword.getText().toString();
                String email = edtemail.getText().toString();

                if (username.equals("") || email.equals("") || password.equals(""))
                    Toast.makeText(ForgotPasswordActivity.this, "All fields are required!", Toast.LENGTH_SHORT).show();
                    if(DB.check_username(edtusername.getText().toString().trim())){

                    }


            }
        });

    }
}