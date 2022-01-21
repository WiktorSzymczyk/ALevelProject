package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import javax.net.ssl.SNIHostName;

public class RegisterActivity extends AppCompatActivity {

    EditText edtusername, edtsurname, edtpassowrd, edt_repeat_password, edtname, edtemail;
    Button register;
    TextView txt_login, banner;
    ProgressDialog mProgressDialog;
    DB_Helper_UserTable DB;
    Spinner level, rank;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        banner = findViewById(R.id.banner_text_splash);
        mProgressDialog = new ProgressDialog(this);

        level = (Spinner) findViewById(R.id.spinner_level);
        rank = (Spinner) findViewById(R.id.spinner_rank);
        edtemail = findViewById(R.id.email);
        register = findViewById(R.id.register_button);
        edtname = findViewById(R.id.name);
        edtpassowrd = findViewById(R.id.password);
        edt_repeat_password = findViewById(R.id.repeat_password);
        edtusername = findViewById(R.id.username);
        edtsurname = findViewById(R.id.surname);
        txt_login = findViewById(R.id.txt_login);
        DB = new DB_Helper_UserTable(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtusername.getText().toString();
                String password = edtpassowrd.getText().toString();
                String repeat_password = edt_repeat_password.getText().toString();
                String name = edtname.getText().toString();
                String surname = edtsurname.getText().toString();
                String email = edtemail.getText().toString();;



            if(username.equals("")||password.equals("")||repeat_password.equals("")||name.equals("")||surname.equals("")||email.equals(""))
                Toast.makeText(RegisterActivity.this, "All fields are required!",Toast.LENGTH_SHORT).show();
            else{
                if(password.equals(repeat_password)){
                    Boolean checkuser = DB.check_username(username);
                    if(checkuser == false){
                        Boolean insert = DB.insertData(username, password, name, surname, email);
                        if(insert == true){
                            Toast.makeText(RegisterActivity.this, "Successfully registered!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(RegisterActivity.this, "Failed to Register!",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegisterActivity.this, "User already exists!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            }
        });

        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, StartActivity.class);
                startActivity(intent);
            }
        });

    }

    }