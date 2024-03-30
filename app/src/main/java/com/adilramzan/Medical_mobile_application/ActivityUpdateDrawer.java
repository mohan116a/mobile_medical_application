package com.adilramzan.Medical_mobile_application;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.adilramzan.Medical_mobile_application.databinding.ActivityUpdateDrawerBinding;

public class ActivityUpdateDrawer extends AppCompatActivity {

    ActivityUpdateDrawerBinding binding;
    private static final int PICK_IMAGE_REQUEST=99;
    private Uri imagePath;
    private Bitmap imageToStore;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityUpdateDrawerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();
        dbHelper=new DBHelper(this);

        binding.ProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choseImage();
            }
        });

        binding.uploadBtnAUD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storeImage();
            }
        });

    }

    private void choseImage() {
        try {

            Intent intent=new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent,PICK_IMAGE_REQUEST);

        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {


            super.onActivityResult(requestCode, resultCode, data);

            if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data!=null && data.getData()!=null){
                imagePath=data.getData();
                imageToStore= MediaStore.Images.Media.getBitmap(getContentResolver(),imagePath);
                binding.ProfileImage.setImageBitmap(imageToStore);
            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private  void storeImage(){
        if(!binding.userName.getText().toString().isEmpty() && !binding.email.getText().toString().isEmpty()
        && !binding.Phone.getText().toString().isEmpty() && !binding.password.getText().toString().isEmpty()
        && binding.ProfileImage.getDrawable()!=null&&imageToStore!=null){
            dbHelper.storeData(new ModelClass(binding.userName.getText().toString(),binding.email.getText().toString(),
                    binding.Phone.getText().toString(),binding.password.getText().toString(),
                    imageToStore));
            Intent intent=new Intent(ActivityUpdateDrawer.this,MainActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(), "fill all the fields", Toast.LENGTH_SHORT).show();
        }
    }

}