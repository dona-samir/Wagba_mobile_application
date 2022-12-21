package com.example.wagba.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wagba.R;
import com.example.wagba.ViewModel.registration;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    private EditText email_EditText;
    private EditText password_EditText;
    private Button login_btn;
    private registration RegistrationViewModel;
    private TextView signup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email_EditText = findViewById(R.id.login_Email);
        password_EditText =findViewById(R.id.login_Password);
        login_btn = findViewById(R.id.btn_login);
        signup=findViewById(R.id.login_signup);
        RegistrationViewModel = new ViewModelProvider(this).get(registration.class);
        RegistrationViewModel.getUserlivedata().observe(this, new Observer<FirebaseUser>() {
                    @Override
                    public void onChanged(FirebaseUser firebaseUser) {
                        if(firebaseUser != null){
                            startActivity(new Intent(login.this, Home.class));
                        }
                    }
                }
        );
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this, signup.class));

            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = email_EditText.getText().toString();
                String password = password_EditText.getText().toString();

                if (email.length() == 0){
                    Toast.makeText(login.this, "please entre an email", Toast.LENGTH_SHORT).show();
                }else if(password.length()==0){
                    Toast.makeText(login.this, "please entre a password", Toast.LENGTH_SHORT).show();
                }else{
                    RegistrationViewModel.login(email,password);
                }
            }
        });



    }

}