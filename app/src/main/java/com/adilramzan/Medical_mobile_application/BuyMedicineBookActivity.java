package com.adilramzan.Medical_mobile_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.adilramzan.Medical_mobile_application.databinding.ActivityBuyMedicineBookBinding;

public class BuyMedicineBookActivity extends AppCompatActivity {

    ActivityBuyMedicineBookBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityBuyMedicineBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        Intent intent=getIntent();
        String[] price=intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date=intent.getStringExtra("date");

        binding.btnBMBABooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //shared preference a small memory to store data for temporary
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();

                Database db=new Database(getApplicationContext(),"Medi Care",null,1);
                db.addOrder(username,binding.etBMBAFullName.getText().toString()
                        ,binding.etBMBAAddress.getText().toString()
                        ,binding.etBMBAContactNumber.getText().toString()
                        ,Integer.parseInt(binding.etBMBAPinCode.getText().toString())
                        ,date.toString(),"",Float.parseFloat(price[1].toString()),"medicine");

                db.removeCart(username,"medicine");
                Toast.makeText(getApplicationContext(), "Booking done successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(BuyMedicineBookActivity.this,HomeActivity.class));

            }
        });




    }

}