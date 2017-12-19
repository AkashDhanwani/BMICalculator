package com.akash.bmicalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

public class BMIResult extends AppCompatActivity {

    TextView tvResult;
    Button btnBack,btnShare;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiresult);

        tvResult = (TextView) findViewById(R.id.tvResult);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnShare = (Button) findViewById(R.id.btnShare);
        sp = getSharedPreferences("UserData",MODE_PRIVATE);

        int count = 0;

        Date date = new Date();
        final String dat = date.toString();

        String name = sp.getString("name","");
        String age = sp.getString("age","");
        String phone = sp.getString("phone","");
        String sex = sp.getString("sex","");

        Intent i = getIntent();
        final double weight = Double.parseDouble(i.getStringExtra("weight"));
        double feet = Double.parseDouble(i.getStringExtra("feet"));
        double inch = Double.parseDouble(i.getStringExtra("inch"));

        while(feet>0)
        {
            inch+=12;
            feet--;
        }
        double height;
        height = inch*2.54;

        double bmi = weight/(height*height);

        bmi=bmi*10000;

        String msg=null;
        if(bmi<18.5)
            msg="You are Underweight";
        if(bmi>=18.5&&bmi<25)
            msg="You are Normal";
        if(bmi>=25&&bmi<30)
            msg="You are Overweight";
        if(bmi>30)
            msg="You are Obese";

        tvResult.setText("Your BMI is "+bmi+" "+msg);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        final String send = "Name = "+name+"\nAge = "+age+"\nPhone = "+phone+"\nSex = "+sex+"\nBMI = "+bmi+"\n"+msg ;
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT,send);
                startActivity(i);
            }
        });
    }
}
