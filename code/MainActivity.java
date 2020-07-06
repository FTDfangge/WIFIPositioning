package com.example.schoolwifipositioning;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.schoolwifipositioning.CLASSES.Client;

public class MainActivity extends AppCompatActivity {

    Button startbtn111;
    //Context context;
   // Button scanBtn;
    //WifiManager wifiManager;
    //BroadcastReceiver broadcastReceiver;
    //TextView textView;
   // IntentFilter intentFilter;
    private int location_request_code = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Client c = Client.getClient();
                c.connect();
            }
        }).start();

        startbtn111 = (Button) findViewById(R.id.startbtn);
        startbtn111.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Start1Activity.class);
                startActivity(intent);
            }
        });
    }
}