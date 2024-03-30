package com.adilramzan.Medical_mobile_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.adilramzan.Medical_mobile_application.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        //shared preference a small memory to store data for temporary
        SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username=sharedPreferences.getString("username","").toString();
        Toast.makeText(getApplicationContext(), "Welcome "+username+" to the Medicare", Toast.LENGTH_SHORT).show();

        //logout card
        binding.cardExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(HomeActivity.this,LoginActivity.class));
            }
        });

        //Find Doctor Card
        binding.cardFindDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,FindDoctorActivity.class));
            }
        });

        //Lab Test Card
        binding.cardLabTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,LabTestActivity.class));
            }
        });

        //Order Details card
        binding.cardOrderDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,OrderDetailsActivity.class));
            }
        });

        //buy medicine card
        binding.cardBuyMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,BuyMedicineActivity.class));
            }
        });

        //health article card
        binding.cardHealthArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,HealthArticlesActivity.class));
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.drawer:
                Toast.makeText(this, "drawer", Toast.LENGTH_SHORT).show();
                Intent intent1=new Intent(HomeActivity.this,MainActivity.class);
                startActivity(intent1);
                break;

            case R.id.settings:
                Toast.makeText(this, "Settings ", Toast.LENGTH_SHORT).show();
//                Intent intent2=new Intent(MainActivity.this,SettingsActivity.class);
//                startActivity(intent2);
                break;
            case R.id.aboutUs:

                Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(HomeActivity.this,AboutUs.class);
                startActivity(intent);
                break;
        }
        return true;
    }



}