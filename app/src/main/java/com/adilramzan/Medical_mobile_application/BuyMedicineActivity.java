package com.adilramzan.Medical_mobile_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.adilramzan.Medical_mobile_application.databinding.ActivityBuyMedicineBinding;
import com.adilramzan.Medical_mobile_application.databinding.ActivityLabTestBinding;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyMedicineActivity extends AppCompatActivity {
    private String[][] packages={
            {"CARDIOLITE 100MG TAB +","","","","800"},
            {"CIPRIN 250MG SYP","","","","500"},
            {"FUSIDERM CREAM","","","","400"},
            {"TERBIN 125MG TAB NEW","","","","550"},
            {"VITRUM TAB  (SEARLE","","","","1200"},
            {"ZOPAN DS (L) (PHARMATEC)","","","","1500"},
            {"ZONOR INJECTION","","","","580"},
            {"STIR-UP TAB","","","","300"},
            {"DEPRICAP CAP (L)","","","","600"}
    };

//    private String[] package_details={
//            "blood count\n"+
//                    "Thyroid CheckUp\n"+
//                    "blood typing\n"+
//                    "Immunity CheckUp\n"+
//                    "bone marrow aspiration\n"+
//                    "Vitamin D-25 Hydroxy\n"+
//                    "enzyme analysis\n"+
//                    "Blood Glucose Fasting",
//            "glucose tolerance test\n",
//            "immunologic blood test\n",
//            "gastric fluid analysis\n",
//            "kidney function test\n"+
//                    "liver function test\n"+
//                    "pregnancy test\n"+
//                    "Full Body CheckUp\n"+
//                    "thyroid function test\n"+
//                    "urinalysis/uroscopy\n"+
//                    "cardiac catheterization"
//
//    };


    private String[] package_details={
            "blood count\n"+
                    "Thyroid CheckUp\n"+
                    "blood typing\n",
            "Immunity CheckUp\n",
            "bone marrow aspiration\n"+
                    "Vitamin D-25 Hydroxy\n"+
                    "enzyme analysis\n",
            "Blood Glucose Fasting"+
                    "glucose tolerance test\n"+
                    "immunologic blood test\n",
            "gastric fluid analysis\n",
            "kidney function test\n"+
                    "liver function test\n",
            "pregnancy test\n"+
                    "Full Body CheckUp\n",
            "thyroid function test\n"+
                    "urinalysis/uroscopy\n",
                    "cardiac catheterization"

    };

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;

    ActivityBuyMedicineBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityBuyMedicineBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        lst=findViewById(R.id.listViewBM);

        //back button
        binding.btnBMBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this,HomeActivity.class));
            }
        });

        //button go to cart buy medicine
        binding.btnBMGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this,CartBuyMedicineActivity.class));
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
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in=new Intent(BuyMedicineActivity.this,BuyMedicineDetailsActivity.class);
                in.putExtra("text1",packages[i][0]);
                in.putExtra("text2",package_details[i]);
                in.putExtra("text3",packages[i][4]);
                startActivity(in);
            }
        });

    }
}