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
    Button scanBtn;
    WifiManager wifiManager;
    //BroadcastReceiver broadcastReceiver;
    TextView textView;
   // IntentFilter intentFilter;
    private int location_request_code = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();
        final Context context = getBaseContext();
        textView = findViewById(R.id.textView15);
        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        final BroadcastReceiver wifiScanReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                boolean success = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false);
                if (success)
                {
                    scanSuccess();
                }
                else
                {
                    scanFailed();
                }
            }
        };

        scanBtn = (Button) findViewById(R.id.button2);
        scanBtn.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
                context.registerReceiver(wifiScanReceiver,intentFilter);
                boolean success = wifiManager.startScan();
                if (success)
                {
                    scanSuccess();
                }
                else
                {
                    scanFailed();
                };
            }
        });







        startbtn111 = (Button) findViewById(R.id.startbtn111);
        startbtn111.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Start1Activity.class);
                startActivity(intent);

            }
        });




    }

    private void scanSuccess()
    {
        textView.append("\nSCAN SUCCESS\n");
        List<ScanResult> scanResultList = wifiManager.getScanResults();
        Iterator<ScanResult> itr = scanResultList.iterator();
        while (itr.hasNext())
        {
            ScanResult result = itr.next();
            textView.append("\n"+result.BSSID+","+result.SSID+","+result.level+"\n");
        }
    }

    private void scanFailed()
    {
        textView.append("\nSCAN FAILED\n");
        List<ScanResult> scanResultList = wifiManager.getScanResults();
        Iterator<ScanResult> itr = scanResultList.iterator();
        while (itr.hasNext())
        {
            ScanResult result = itr.next();
            textView.append("\n"+result.BSSID+","+result.SSID+","+result.level+"\n");
        }
    }

    private void checkPermission(){
        String[] REQUEST_PERMISSIONS = {android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.CHANGE_WIFI_STATE, Manifest.permission.ACCESS_WIFI_STATE};
        int LocPermission = ContextCompat.checkSelfPermission(this, REQUEST_PERMISSIONS[0]);
        int AccPermission = ContextCompat.checkSelfPermission(this, REQUEST_PERMISSIONS[1]);
        int ChPermission = ContextCompat.checkSelfPermission(this, REQUEST_PERMISSIONS[1]);
        if (LocPermission != PackageManager.PERMISSION_GRANTED || AccPermission != PackageManager.PERMISSION_GRANTED || ChPermission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, REQUEST_PERMISSIONS, location_request_code);
        }
        Log.d("mark", "The permissions has been satisfied");
    }
}
