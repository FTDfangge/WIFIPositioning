package com.example.schoolwifipositioning;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class OthersActivity extends AppCompatActivity {

    Button returnbtn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.others_layout);

        returnbtn3 = (Button) findViewById(R.id.returnbtn3);

        returnbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });


    }
}
