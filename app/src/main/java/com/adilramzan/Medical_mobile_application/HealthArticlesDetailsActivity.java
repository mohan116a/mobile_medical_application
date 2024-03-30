package com.adilramzan.Medical_mobile_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.adilramzan.Medical_mobile_application.databinding.ActivityHealthArticlesDetailsBinding;

public class HealthArticlesDetailsActivity extends AppCompatActivity {

    ActivityHealthArticlesDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityHealthArticlesDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        Intent intent=getIntent();
        binding.tvHADTitle.setText(intent.getStringExtra("text1"));

        Bundle bundle=getIntent().getExtras();
        if(bundle  !=null){
            int resId=bundle.getInt("text2");
            binding.ivHAD.setImageResource(resId);
        }
        //back button
        binding.btnBackHAD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HealthArticlesDetailsActivity.this,HealthArticlesActivity.class));
            }
        });
    }
}