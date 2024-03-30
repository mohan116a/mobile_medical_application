package com.adilramzan.Medical_mobile_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.adilramzan.Medical_mobile_application.databinding.ActivityLoginBinding;
import com.adilramzan.Medical_mobile_application.databinding.ActivityRegisterBinding;

public class docRegisterActivity extends AppCompatActivity {
        EditText edUsername, edpassword, edEmail, edconfirm;
        Button btn;
        TextView tv;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_doc_register);

            edUsername = findViewById(R.id.editTextRegusername);
            edEmail = findViewById(R.id.editTextRegemail);
            edpassword = findViewById(R.id.editTextRegPassword);
            edconfirm = findViewById(R.id.editTextRegconfirmpassword);
            btn= findViewById(R.id.buttonRegister);
            tv = findViewById(R.id.textViewexistinguser);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(docRegisterActivity.this, docloginActivity.class));
                }
            });
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String username = edUsername.getText().toString();
                    String password = edpassword.getText().toString();
                    String email = edEmail.getText().toString();
                    String confirm = edconfirm.getText().toString();
                    Database db = new Database(getApplicationContext(),"healthcare",null,1);
                    if(username.length()==0 || password.length()==0 || email.length()==0 || confirm.length()==0){
                        Toast.makeText(getApplicationContext(),"fill all details",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if (password.compareTo(confirm)==0) {
                            if (isValid(password)) {
                                db.register(username,email,password);
                                Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(docRegisterActivity.this, docloginActivity.class));
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"password must contain atlease 8 characters, having letter,digit and a special character",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(getApplicationContext(),"password and confirm password not matched",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
        public static boolean isValid(String passwordhere){
            int f1=0,f2=0,f3=0;
            if (passwordhere.length() < 8) {
                return false;
            } else {
                for (int p = 0; p < passwordhere.length(); p++) {
                    if (Character.isLetter(passwordhere.charAt(p))) {
                        f1 = 1;
                    }
                }
                for (int r = 0; r < passwordhere.length(); r++) {
                    if (Character.isDigit(passwordhere.charAt(r))) {
                        f2 = 1;
                    }
                }
                for (int s = 0; s < passwordhere.length(); s++) {
                    char c = passwordhere.charAt(s);
                    if (c >= 33 && c <= 46 || c == 64) {
                        f3 = 1;
                    }
                }
                if(f1==1 && f2==1 && f3==1)
                    return true;
                return false;
            }
        }
    }
