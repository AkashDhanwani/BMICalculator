package com.akash.bmicalculator;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BMIData extends AppCompatActivity {

    TextView tvWelcome;
    Spinner spnFeet,spnInch;
    EditText etWeight;
    Button btnCalculate;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmidata);

        tvWelcome = (TextView) findViewById(R.id.tvWelcome);
        spnFeet = (Spinner) findViewById(R.id.spnFeet);
        spnInch = (Spinner) findViewById(R.id.spnInch);
        etWeight = (EditText) findViewById(R.id.etWeight);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        sp = getSharedPreferences("UserData",MODE_PRIVATE);

        String name = sp.getString("name","");
        tvWelcome.setText("Welcome "+name);

        String[] Feet = {"1","2","3","4","5","6","7"};
        ArrayAdapter feetadapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,Feet);
        spnFeet.setAdapter(feetadapter);

        String[] Inch = {"0","1","2","3","4","5","6","7","8","9","10","11"};
        ArrayAdapter inchadapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,Inch);
        spnInch.setAdapter(inchadapter);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String weight = etWeight.getText().toString();
                String feet = spnFeet.getSelectedItem().toString();
                String inch = spnInch.getSelectedItem().toString();

                if ( weight.length() == 0)
                {
                    etWeight.setError("Please enter some Weight");
                    etWeight.requestFocus();
                    return;
                }

                Intent i = new Intent(getApplicationContext(),BMIResult.class);
                i.putExtra("weight",weight);
                i.putExtra("feet",feet);
                i.putExtra("inch",inch);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }//end of onCreateOpttionsMenu

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.Website:
                Intent i=new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://" + "en.m.wikipedia.org/wiki/Body_mass_index"));
                startActivity(i);
                break;

            case R.id.About:
                Snackbar.make(findViewById(android.R.id.content),"Developed by Fundroid Classes",Snackbar.LENGTH_LONG).show();
                break;

            case R.id.Rate:
                Intent i1 = new Intent(getApplicationContext(),Rate.class);
                startActivity(i1);
        }

        return super.onOptionsItemSelected(item);
    }//end of onOptionsItemSelected

    public void onBackPressed()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Do you want to close this application?");
        builder.setCancelable(false);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alertDialog=builder.create();
        alertDialog.setTitle("Exit");
        alertDialog.show();
    }//end of onBackPressed

}


