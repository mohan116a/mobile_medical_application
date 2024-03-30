package com.adilramzan.Medical_mobile_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.adilramzan.Medical_mobile_application.databinding.ActivityFindDoctorBinding;
import com.adilramzan.Medical_mobile_application.databinding.ActivityLoginBinding;

public class FindDoctorActivity extends AppCompatActivity {

    ActivityFindDoctorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityFindDoctorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        //back button to home activity
        binding.cardFDBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FindDoctorActivity.this,HomeActivity.class));
            }
        });

        binding.cardFDFamilyPhysician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FindDoctorActivity.this,DoctorDetailsActivity.class);
                intent.putExtra("title","Family Physician");
                startActivity(intent);
            }
        });

        binding.cardFDDietician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FindDoctorActivity.this,DoctorDetailsActivity.class);
                intent.putExtra("title","Dietician");
                startActivity(intent);
            }
        });

        binding.cardFDDentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FindDoctorActivity.this,DoctorDetailsActivity.class);
                intent.putExtra("title","Dentist");
                startActivity(intent);
            }
        });

        binding.cardFDSurgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FindDoctorActivity.this,DoctorDetailsActivity.class);
                intent.putExtra("title","Surgeon");
                startActivity(intent);
            }
        });

        binding.cardFDCardiologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FindDoctorActivity.this,DoctorDetailsActivity.class);
                intent.putExtra("title","Cardiologists");
                startActivity(intent);
            }
        });


    }


}