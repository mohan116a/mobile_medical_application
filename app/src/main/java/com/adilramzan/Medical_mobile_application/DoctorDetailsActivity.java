package com.adilramzan.Medical_mobile_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.adilramzan.Medical_mobile_application.databinding.ActivityDoctorDetailsBinding;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {

    private  String[][]doctor_details1={
        {"Doctor Name : Mirza Omar","Hospital Address : Okara","Exp : 7yrs","Mobile No : 923045467888","2000"},
        {"Doctor Name : Nimra Malik","Hospital Address : Lahore","Exp : 5yrs","Mobile No : 923035467878","3000"},
        {"Doctor Name : Ali","Hospital Address : RawalPindi","Exp : 9yrs","Mobile No : 923077567983","2500"},
        {"Doctor Name : Ramazan","Hospital Address : Dipalpur","Exp : 3yrs","Mobile No : 923045747568","1500"},
        {"Doctor Name : Kashif ","Hospital Address : Lahore","Exp : 10yrs","Mobile No : 923075237859","2000"}
    };
    private  String[][]doctor_details2={
            {"Doctor Name : Imran","Hospital Address : Okara","Exp : 7yrs","Mobile No : 923045467888","2000"},
            {"Doctor Name : Areeba Malik","Hospital Address : Lahore","Exp : 5yrs","Mobile No : 923035467878","3000"},
            {"Doctor Name : Ali Ashraf","Hospital Address : RawalPindi","Exp : 9yrs","Mobile No : 923077567983","2500"},
            {"Doctor Name : Umar","Hospital Address : Dipalpur","Exp : 3yrs","Mobile No : 923045747568","1500"},
            {"Doctor Name : Munir ","Hospital Address : Lahore","Exp : 10yrs","Mobile No : 923075237859","2000"}
    };
    private  String[][]doctor_details3={
            {"Doctor Name : Farhan","Hospital Address : Okara","Exp : 7yrs","Mobile No : 923045467888","2000"},
            {"Doctor Name : Alisa Malik","Hospital Address : Lahore","Exp : 5yrs","Mobile No : 923035467878","3000"},
            {"Doctor Name : Aleena","Hospital Address : RawalPindi","Exp : 9yrs","Mobile No : 923077567983","2500"},
            {"Doctor Name : Rehan","Hospital Address : Dipalpur","Exp : 3yrs","Mobile No : 923045747568","1500"},
            {"Doctor Name : Aadil ","Hospital Address : Lahore","Exp : 10yrs","Mobile No : 923075237859","2000"}
    };
    private  String[][]doctor_details4={
            {"Doctor Name : Rizwan","Hospital Address : Okara","Exp : 7yrs","Mobile No : 923045467888","2000"},
            {"Doctor Name : Naseem ","Hospital Address : Lahore","Exp : 5yrs","Mobile No : 923035467878","3000"},
            {"Doctor Name : Tasneem","Hospital Address : RawalPindi","Exp : 9yrs","Mobile No : 923077567983","2500"},
            {"Doctor Name : Moeez","Hospital Address : Dipalpur","Exp : 3yrs","Mobile No : 923045747568","1500"},
            {"Doctor Name : Shehroz ","Hospital Address : Lahore","Exp : 10yrs","Mobile No : 923075237859","2000"}
    };
    private  String[][]doctor_details5={
            {"Doctor Name : Sarib","Hospital Address : Okara","Exp : 7yrs","Mobile No : 923045467888","2000"},
            {"Doctor Name : Malik Saad","Hospital Address : Lahore","Exp : 5yrs","Mobile No : 923035467878","3000"},
            {"Doctor Name : Bilal","Hospital Address : RawalPindi","Exp : 9yrs","Mobile No : 923077567983","2500"},
            {"Doctor Name : Haziq","Hospital Address : Dipalpur","Exp : 3yrs","Mobile No : 923045747568","1500"},
            {"Doctor Name : Asad ","Hospital Address : Lahore","Exp : 10yrs","Mobile No : 923075237859","2000"}
    };

    ActivityDoctorDetailsBinding binding;

    String[][] doctor_details={};
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDoctorDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        //setting text
        Intent intent=getIntent();
        String title=intent.getStringExtra("title");
        binding.textViewDDTitle.setText(title);

        //string
        if(title.compareTo("Family Physicians")==0)
            doctor_details=doctor_details1;
        else
        if(title.compareTo("Dietician")==0)
            doctor_details=doctor_details2;
        else
        if(title.compareTo("Dentist")==0)
            doctor_details=doctor_details3;
        else
        if(title.compareTo("Surgeon")==0)
            doctor_details=doctor_details4;
        else
            doctor_details=doctor_details5;


        //Back button
        binding.btnDDBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class));
            }
        });

        list = new ArrayList();
        for(int i=0; i<doctor_details.length; i++){
            item =new HashMap<String,String>();

            item.put("line1",doctor_details[i][0]);
            item.put("line2",doctor_details[i][1]);
            item.put("line3",doctor_details[i][2]);
            item.put("line4",doctor_details[i][3]);
            item.put("line5","Consultant fee : "+doctor_details[i][4]+"/-");
            list.add(item);

            sa =new SimpleAdapter(this,list,
                    R.layout.multi_lines,
                    new String[]{"line1","line2","line3","line4","line5"},
                    new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
                    );

            ListView lst=findViewById(R.id.listViewDD);
            lst.setAdapter(sa);

            //to pass data from one activity to other
            lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent in= new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
                    in.putExtra("text1",title);
                    in.putExtra("text2",doctor_details[i][0]);
                    in.putExtra("text3",doctor_details[i][1]);
                    in.putExtra("text4",doctor_details[i][3]);
                    in.putExtra("text5",doctor_details[i][4]);

                    startActivity(in);

                }
            });

        }

    }
}