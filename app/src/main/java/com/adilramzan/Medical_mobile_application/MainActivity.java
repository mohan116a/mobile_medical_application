package com.adilramzan.Medical_mobile_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.adilramzan.Medical_mobile_application.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private DrawerLayout drawer_layout;
    private  long pressedTime;
    private ActionBarDrawerToggle mToggle;
    Toolbar toolbar;
    Button upload;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //getSupportActionBar().hide();

//        upload=findViewById(R.id.uploadData);
//
//        upload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this,ActivityUpdateDrawer.class));
//            }
//        });


        //actionBar.set(R.id.app_bar_nav_menu);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);

        //-------------------------side bar navigation menue code-----------------------

        drawer_layout= findViewById(R.id.drawerLayout);
        NavigationView navigationview = findViewById(R.id.sidebar_navigation);


        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.nav_add) {
                    Intent add_intent=new Intent(MainActivity.this, ActivityUpdateDrawer.class);
                    startActivity(add_intent);
                }
                else if(id==R.id.nav_update){
//                    Intent add_intent=new Intent(MainActivity.this, update.class);
//                    startActivity(add_intent);
                }
                else if(id==R.id.nav_search){
//                    Intent add_intent=new Intent(MainActivity.this, search.class);
//                    startActivity(add_intent);
                }
                else
                    if(id==R.id.about_page){
                    Intent add_intent=new Intent(MainActivity.this, AboutUs.class);
                    startActivity(add_intent);
                }
                else if(id==R.id.nav_Logout){
                    finish();
                }
                else
                    Toast.makeText(MainActivity.this, "Page not found!", Toast.LENGTH_SHORT).show();

                drawer_layout.closeDrawer(GravityCompat.START);

                return true;
            }
        });

        View header=navigationview.getHeaderView(0);
        ImageView navImage=(ImageView) header.findViewById(R.id.pimageND);
        TextView navUserName=(TextView) header.findViewById(R.id.tvUserNameND);
        TextView navEmail=(TextView) header.findViewById(R.id.tvMailND);

        DBHelper dbHelper=new DBHelper(this);
        Cursor cursor=dbHelper.getUser();
        if(cursor.getCount()==0){
            Toast.makeText(getApplicationContext(), "No Entries", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                navUserName.setText(""+cursor.getString(0));
                navEmail.setText(""+cursor.getString(1));

                byte[] imageByte=cursor.getBlob(4);
                Bitmap bitmap= BitmapFactory.decodeByteArray(imageByte,0,imageByte.length);
                navImage.setImageBitmap(bitmap);
            }
        }




    }

    @Override
    public void onBackPressed() {
        drawer_layout=findViewById(R.id.drawerLayout);
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START);
            return;
        } else {
            if (pressedTime + 2000 > System.currentTimeMillis()) {
                super.onBackPressed();
                finish();
            } else {
                Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
            }
            pressedTime = System.currentTimeMillis();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Integer item_id=item.getItemId();

        if(item_id==android.R.id.home)
            drawer_layout.openDrawer(GravityCompat.START);


        return super.onOptionsItemSelected(item);
    }


}