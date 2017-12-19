package com.akash.bmicalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class Rate extends AppCompatActivity {

    RatingBar rbStar;
    Button btnRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        rbStar = (RatingBar) findViewById(R.id.rbStar);
        btnRate = (Button) findViewById(R.id.btnRate);

        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float rat = rbStar.getRating();
                Toast.makeText(Rate.this, "Thanks for Rating us "+rat+" Stars", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
