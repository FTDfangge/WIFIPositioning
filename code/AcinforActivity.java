package com.example.schoolwifipositioning;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AcinforActivity extends AppCompatActivity {

    Button returnbtn12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accountinfor_layout);

        returnbtn12 = (Button) findViewById(R.id.returnbtn12);

        returnbtn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });


    }
}
