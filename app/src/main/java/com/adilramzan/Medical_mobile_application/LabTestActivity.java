package com.adilramzan.Medical_mobile_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.adilramzan.Medical_mobile_application.databinding.ActivityLabTestBinding;

import java.util.ArrayList;
import java.util.HashMap;

public class LabTestActivity extends AppCompatActivity {

    private String[][] packages={
            {"Package 1: Full Body CheckUp","","","","3000"},
            {"Package 2: Blood Glucose Fasting","","","","1500"},
            {"Package 3: Vitamin D-25 Hydroxy","","","","4000"},
            {"Package 4: Thyroid CheckUp","","","","3500"},
            {"Package 5: Immunity CheckUp","","","","4000"},
    };

    private String[] package_details={
            "blood count\n"+
                    "Thyroid CheckUp\n"+
                    "blood typing\n"+
                    "Immunity CheckUp\n"+
                    "bone marrow aspiration\n"+
                    "Vitamin D-25 Hydroxy\n"+
                    "enzyme analysis\n"+
                    "Blood Glucose Fasting",
            "glucose tolerance test\n",
            "immunologic blood test\n",
            "gastric fluid analysis\n",
            "kidney function test\n"+
                    "liver function test\n"+
                    "pregnancy test\n"+
                    "Full Body CheckUp\n"+
                    "thyroid function test\n"+
                    "urinalysis/uroscopy\n"+
                    "cardiac catheterization"

    };

    ActivityLabTestBinding binding;
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLabTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        listView=findViewById(R.id.listViewLT);
        //back button
        binding.btnLTBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestActivity.this,HomeActivity.class));
            }
        });

        //listView
        list = new ArrayList();
        for(int i=0; i<packages.length; i++) {
            item = new HashMap<String, String>();

            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", "Total Fee : " + packages[i][4] + "/-");
            list.add(item);
        }

        sa =new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
        );
        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in=new Intent(LabTestActivity.this,LabTestDetailsActivity.class);
                in.putExtra("text1",packages[i][0]);
                in.putExtra("text2",package_details[i]);
                in.putExtra("text3",packages[i][4]);
                startActivity(in);
            }
        });

        //cart button
        binding.btnLTCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestActivity.this,CartLabActivity.class));
            }
        });

    }

}