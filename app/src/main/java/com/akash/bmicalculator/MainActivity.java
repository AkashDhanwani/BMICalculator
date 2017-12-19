package com.akash.bmicalculator;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    EditText etName,etAge,etPhoneNumber;
    RadioGroup rgGender;
    Button btnRegister;
    SharedPreferences spn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);
        etAge = (EditText) findViewById(R.id.etAge);
        etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
        rgGender = (RadioGroup) findViewById(R.id.rgGender);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        spn1 = getSharedPreferences("UserData",MODE_PRIVATE);

        if(spn1.getBoolean("ne",false) == false) {
            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String name = etName.getText().toString();
                    String age = etAge.getText().toString();
                    String phone = etPhoneNumber.getText().toString();

                    int pos = rgGender.getCheckedRadioButtonId();
                    RadioButton rb = (RadioButton) findViewById(pos);
                    String sex = rb.getText().toString();

                    if(name.length() == 0)
                    {
                        etName.setError("Please enter Name");
                        etName.requestFocus();
                        return;
                    }
                    if(age.length() == 0)
                    {
                        etAge.setError("Please enter your Age");
                        etAge.requestFocus();
                        return;
                    }
                    if(phone.length() != 10)
                    {
                        etPhoneNumber.setError("Please enter 10 digit Number");
                        etPhoneNumber.requestFocus();
                        return;
                    }
                    SharedPreferences.Editor editor = spn1.edit();
                    editor.putString("name", name);
                    editor.putString("age", age);
                    editor.putString("phone", phone);
                    editor.putString("sex",sex);
                    editor.putBoolean("ne",true);
                    editor.commit();
                    Intent i = new Intent(getApplicationContext(),BMIData.class);
                    startActivity(i);
                    finish();
                }
            });
        }
        else {
            Intent i = new Intent(getApplicationContext(), BMIData.class);
            startActivity(i);
            finish();
        }
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
