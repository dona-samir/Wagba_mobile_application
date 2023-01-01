package com.example.wagba.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.lifecycle.Observer;

import com.example.wagba.Model.users;
import com.example.wagba.ViewModel.registrationViewModel;
import com.example.wagba.R;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {

    private EditText name_EditText;
    private EditText email_EditText;
    private EditText password_EditText;
    private EditText confirm_password_EditText;
    private EditText phone_no_EditText;
    private Button sign_up_btn;
    private TextView accountexists;
    private registrationViewModel RegistrationViewModel;
    FirebaseDatabase db;
    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name_EditText = findViewById(R.id.signup_name);
        email_EditText =findViewById(R.id.signup_Email);
        phone_no_EditText =findViewById(R.id.signup_phone_no);
        password_EditText =findViewById(R.id.signup_Password);
        confirm_password_EditText =findViewById(R.id.signup_confirm);
        sign_up_btn =findViewById(R.id.btn_sign);
        accountexists =findViewById(R.id.login_accountexists);
        RegistrationViewModel = new ViewModelProvider(this).get(registrationViewModel.class);
        RegistrationViewModel.getUserlivedata().observe(this, new Observer<FirebaseUser>() {
                    @Override
                    public void onChanged(FirebaseUser firebaseUser) {
                        String email = email_EditText.getText().toString();
                        String name = name_EditText.getText().toString();
                        String phone_no = phone_no_EditText.getText().toString();
                        if(firebaseUser != null){
                            users user = new users(name,email,phone_no);
                            db =FirebaseDatabase.getInstance();
                            reference = db.getReference("Users_Node");
                            reference.child(firebaseUser.getUid()).setValue(user);
                            startActivity(new Intent(signup.this, login.class));
                        }
                    }
                }
        );
        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = email_EditText.getText().toString();
                String name = name_EditText.getText().toString();
                String password = password_EditText.getText().toString();
                String confirm_password = confirm_password_EditText.getText().toString();
                String phone_no = phone_no_EditText.getText().toString();
                if(name.length() ==0 ){
                    Toast.makeText(signup.this, "please entre your name", Toast.LENGTH_SHORT).show();
                }else if(email.length()==0 ){
                    Toast.makeText(signup.this, "please entre your email", Toast.LENGTH_SHORT).show();
                }else if( phone_no.length()!=11){
                    Toast.makeText(signup.this, "please entre a valid phone number", Toast.LENGTH_SHORT).show();

                } else if(password.length() < 6){
                    Toast.makeText(signup.this, "please entre valid password more than 5 digit", Toast.LENGTH_SHORT).show();

                }else if (confirm_password.length()==0){
                    Toast.makeText(signup.this, "please rewrite password", Toast.LENGTH_SHORT).show();

                }else if(!confirm_password.equals(password)){
                    Toast.makeText(signup.this, "password didn't match ", Toast.LENGTH_SHORT).show();

                }else{
                    RegistrationViewModel.signup(email,password ,name , phone_no);
                }
            }
        });
        accountexists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signup.this, login.class));
            }
        });
    }
}