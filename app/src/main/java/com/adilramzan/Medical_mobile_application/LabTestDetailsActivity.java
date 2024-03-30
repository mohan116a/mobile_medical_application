package com.adilramzan.Medical_mobile_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.adilramzan.Medical_mobile_application.databinding.ActivityLabTestDetailsBinding;

public class LabTestDetailsActivity extends AppCompatActivity {

    ActivityLabTestDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLabTestDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        binding.etMultilineLTDA.setKeyListener(null);

        Intent intent=getIntent();
        binding.tvLTDAPackage.setText(intent.getStringExtra("text1"));
        binding.etMultilineLTDA.setText(intent.getStringExtra("text2"));
        binding.tvCostLTDA.setText("Total Fee: "+intent.getStringExtra("text3")+"/-");


        //back button
        binding.btnLTDABack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestDetailsActivity.this,LabTestActivity.class));
            }
        });

        //add cart button
        binding.btnLTDAAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //shared preference a small memory to store data for temporary
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();
                String product=binding.tvLTDAPackage.getText().toString();
                float price=Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db=new Database(getApplicationContext(),"Medi Care",null,1);

                if(db.checkCart(username,product)==1){
                    Toast.makeText(getApplicationContext(), "Product Already Added", Toast.LENGTH_SHORT).show();
                }else{
                    db.addCart(username,product,price,"lab");
                    Toast.makeText(getApplicationContext(), "Record Inserted to cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestDetailsActivity.this,LabTestActivity.class));
                }
            }
        });



    }


}