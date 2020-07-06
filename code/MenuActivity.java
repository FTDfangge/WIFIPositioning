package com.example.schoolwifipositioning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    ImageButton wode;
    ImageButton sousuobtn;
    ImageButton zixishi;
    ImageButton bangongshi;
    ImageButton qita;
    ImageButton quanbu;
    ImageButton zhoubian;
    ImageButton dingwei;
    ImageButton resou;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        wode = (ImageButton) findViewById(R.id.mine);
        sousuobtn = (ImageButton) findViewById(R.id.searchbtn);
        zixishi = (ImageButton) findViewById(R.id.study);
        bangongshi = (ImageButton) findViewById(R.id.office);
        qita = (ImageButton) findViewById(R.id.other);
        quanbu = (ImageButton) findViewById(R.id.all);
        zhoubian = (ImageButton) findViewById(R.id.nearby);
        dingwei = (ImageButton) findViewById(R.id.location);
        resou = (ImageButton) findViewById(R.id.topic);


        wode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this,MineActivity.class);
                startActivity(intent);

            }
        });

        zixishi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this,ClassroomActivity.class);
                startActivity(intent);

            }
        });

        bangongshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this,OfficeActivity.class);
                startActivity(intent);

            }
        });

        qita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this,OthersActivity.class);
                startActivity(intent);

            }
        });

        quanbu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this,AllActivity.class);
                startActivity(intent);

            }
        });

        zhoubian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this,RecommendActivity.class);
                startActivity(intent);

            }
        });

        dingwei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this,LocationActivity.class);
                startActivity(intent);

            }
        });

        resou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this,HotActivity.class);
                startActivity(intent);

            }
        });









    }
}
