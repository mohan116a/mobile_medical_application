package com.adilramzan.Medical_mobile_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.adilramzan.Medical_mobile_application.databinding.ActivityHealthArticlesBinding;

import java.util.ArrayList;
import java.util.HashMap;

public class HealthArticlesActivity extends AppCompatActivity {
    private String[][] health_details={
            {"Walk daily","","","","Click here for more details"},
            {"Home Care of Dengue","","","","Click here for more details"},
            {"Stop Smoking","","","","Click here for more details"},
            {"Menstrual Cramps","","","","Click here for more details"},
            {"Healthy Gut","","","","Click here for more details"}

    };

    private  int[] images={
            R.drawable.health1,
            R.drawable.health2,
            R.drawable.health3,
            R.drawable.health4,
            R.drawable.health5
    };

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;

    ActivityHealthArticlesBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityHealthArticlesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        lst=findViewById(R.id.listViewHA);

        //back button
        binding.btnBackHA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HealthArticlesActivity.this,HomeActivity.class));
            }
        });

        //listView
        list = new ArrayList();
        for(int i=0; i<health_details.length; i++) {
            item = new HashMap<String, String>();

            item.put("line1", health_details[i][0]);
            item.put("line2", health_details[i][1]);
            item.put("line3", health_details[i][2]);
            item.put("line4", health_details[i][3]);
            item.put("line5", health_details[i][4]);
            list.add(item);
        }

        sa =new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
        );
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in=new Intent(HealthArticlesActivity.this,HealthArticlesDetailsActivity.class);
                in.putExtra("text1",health_details[i][0]);
                in.putExtra("text2",images[i]);
                startActivity(in);
            }
        });


    }
}