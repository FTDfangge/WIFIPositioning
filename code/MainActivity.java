import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button startbtn111;
    //Context context;
    //Button scanBtn;
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
                if(!c.isConnect()) {
                    c.connect();
                }
            }
        }).start();

        startbtn111 = (Button) findViewById(R.id.startbtn111);
        startbtn111.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Start1Activity.class);
                startActivity(intent);

            }
        });
    }   
}
