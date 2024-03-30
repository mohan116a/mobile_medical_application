package com.adilramzan.Medical_mobile_application;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.adilramzan.Medical_mobile_application.databinding.ActivityBookAppointmentBinding;

import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {

    ActivityBookAppointmentBinding binding;

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateButton,timeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityBookAppointmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        binding.etTextAppFullName.setKeyListener(null);
        binding.etTextAppAddress.setKeyListener(null);
        binding.etTextAppContactNumber.setKeyListener(null);
        binding.etTextAppFees.setKeyListener(null);

        //fetching or getting data from other activity through intent
        Intent intent=getIntent();
        String title=intent.getStringExtra("text1");
        String fullname=intent.getStringExtra("text2");
        String address=intent.getStringExtra("text3");
        String contact=intent.getStringExtra("text4");
        String fees=intent.getStringExtra("text5");

        binding.textViewAppTitle.setText(title);
        binding.etTextAppFullName.setText(fullname);
        binding.etTextAppAddress.setText(address);
        binding.etTextAppContactNumber.setText(contact);
        binding.etTextAppFees.setText("Consultant Fees: "+fees+"/-");

        dateButton = findViewById(R.id.appDateBtn);
        timeButton = findViewById(R.id.appTimeBtn);

        //dateButton=binding.appDateBtn;
        //timeButton=binding.appTimeBtn;

        //date picker function
        initDatePicker();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        //time picker function
        initTimePicker();
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });

        //back button
        binding.etAppBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookAppointmentActivity.this,FindDoctorActivity.class));
            }
        });

        //Book Appointment Button
        binding.etBookAppointmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database db=new Database(getApplicationContext(),"Medi Care",null,1);
                //shared preference a small memory to store data for temporary
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();

                if(db.checkAppointmentExists(username,title+"=> "+fullname,address,contact,dateButton.getText().toString(),timeButton.getText().toString())==1){
                    Toast.makeText(getApplicationContext(), "Appointment booked already", Toast.LENGTH_LONG).show();
                }else{
                    db.addOrder(username,title+"=> "+fullname,address,contact,0,dateButton.getText().toString(),timeButton.getText().toString(),Float.parseFloat(fees),"appointment");
                    Toast.makeText(getApplicationContext(), "Appointment done successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(BookAppointmentActivity.this,HomeActivity.class));
                }

            }
        });


    }

    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1=i1+1;
               dateButton.setText(i2+"/"+i1+"/"+i);
            }
        };

        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DAY_OF_MONTH);

        int style= AlertDialog.THEME_HOLO_DARK;
        datePickerDialog=new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);
    }

    private  void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                timeButton.setText(i+":"+i1);
            }
        };

        Calendar cal=Calendar.getInstance();
        int hrs=cal.get(Calendar.HOUR);
        int mins=cal.get(Calendar.MINUTE);

        int style=AlertDialog.THEME_HOLO_DARK;
        timePickerDialog=new TimePickerDialog(this,style,timeSetListener,hrs,mins,true);
    }


}