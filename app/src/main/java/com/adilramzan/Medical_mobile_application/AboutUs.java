package com.adilramzan.Medical_mobile_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.adilramzan.Medical_mobile_application.databinding.ActivityAboutUsBinding;
import com.bumptech.glide.Glide;

public class AboutUs extends AppCompatActivity {

    ActivityAboutUsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAboutUsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        //ImageView about_img = findViewById(R.id.about_screen_image);
        Glide.with(this).load(R.drawable.aboutuspp).into(binding.aboutUsImg);

        //back button
        binding.btnAUBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AboutUs.this,HomeActivity.class));
            }
        });

    }

    @Override
    public void onBackPressed(){
        finish();
        super.onBackPressed();
    }

}