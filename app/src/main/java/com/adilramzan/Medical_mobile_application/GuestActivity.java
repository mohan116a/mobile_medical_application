package com.adilramzan.Medical_mobile_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.adilramzan.Medical_mobile_application.databinding.ActivityGuestBinding;

public class GuestActivity extends AppCompatActivity {
    EditText edUsername, edpassword;
    Button btn;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_login);

        edUsername = findViewById(R.id.editTextloginusername);
        edpassword = findViewById(R.id.editTextloginPassword);
        btn= findViewById(R.id.buttonlogin);
        tv = findViewById(R.id.textViewsignup);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString();
                String password = edpassword.getText().toString();
                Database db = new Database(getApplicationContext(),"healthcare",null,1);
                if(username.length()==0 || password.length()==0){
                    Toast.makeText(getApplicationContext(),"fill username or password",Toast.LENGTH_SHORT).show();
                }else{
                    if(db.login(username,password)==1){
                        Toast.makeText(getApplicationContext(),"login succes",Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username",username);
                        // to save our data with key and value.
                        editor.apply();
                        startActivity(new Intent(GuestActivity.this, HomeActivity.class));
                    }else {
                        Toast.makeText(getApplicationContext(), "invalid username and password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuestActivity.this, RegisterActivity.class));
            }
        });
    }
}