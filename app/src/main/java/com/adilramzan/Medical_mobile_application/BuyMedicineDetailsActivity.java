package com.adilramzan.Medical_mobile_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.adilramzan.Medical_mobile_application.databinding.ActivityBuyMedicineDetailsBinding;

public class BuyMedicineDetailsActivity extends AppCompatActivity {

    ActivityBuyMedicineDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityBuyMedicineDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        binding.etBMDMultiline.setKeyListener(null);

        Intent intent=getIntent();
        binding.tvBMDPackage.setText(intent.getStringExtra("text1"));
        binding.etBMDMultiline.setText(intent.getStringExtra("text2"));
        binding.tvBMDCost.setText("Total Price: "+intent.getStringExtra("text3")+"/-");


        //back button
        binding.btnBMDBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineDetailsActivity.this,BuyMedicineActivity.class));
            }
        });

        //button go to cart
        binding.btnBMDAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //shared preference a small memory to store data for temporary
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();

                String product=binding.tvBMDPackage.getText().toString();
                float price=Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db=new Database(getApplicationContext(),"Medi Care",null,1);

                if(db.checkCart(username,product)==1){
                    Toast.makeText(getApplicationContext(), "Product Already Added", Toast.LENGTH_SHORT).show();
                }else{
                    db.addCart(username,product,price,"medicine");
                    Toast.makeText(getApplicationContext(), "Record Inserted to cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuyMedicineDetailsActivity.this,BuyMedicineActivity.class));
                }

            }
        });

    }
}