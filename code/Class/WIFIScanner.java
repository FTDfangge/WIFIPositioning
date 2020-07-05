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
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.schoolwifipositioning.R;

import java.util.Iterator;
import java.util.List;

public class WIFIScanner extends AppCompatActivity {
    private int location_request_code = 100;
    private WifiManager wifiManager;
    private static final WIFIScanner wifiScanner = new WIFIScanner();
    private TextView textView;
    private List<ScanResult> resultList;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wifi_scan_layout);

    }

    private WIFIScanner()
    {
        checkPermission();
        textView = findViewById(R.id.textView16);
        Context context = getBaseContext();
        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        BroadcastReceiver wifiScanReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                boolean success = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false);
                if (success)
                {
                    resultList = scanSuccess();
                }
                else
                {
                    scanFailed();
                }
            }
        };

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        context.registerReceiver(wifiScanReceiver,intentFilter);
        boolean success = wifiManager.startScan();
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

    private List<ScanResult> scanSuccess()
    {
        textView.append("\nSCAN SUCCESS\n");
        List<ScanResult> scanResultList = wifiManager.getScanResults();
        Iterator<ScanResult> itr = scanResultList.iterator();
        while (itr.hasNext())
        {
            ScanResult result = itr.next();
            textView.append("\n"+result.BSSID+","+result.SSID+","+result.level+"\n");
        }
        return scanResultList;
    }

    public List<ScanResult> getResultList()
    {
        return resultList;
    }

    private void scanFailed()
    {
        textView.append("\nSCAN FAILED\n");

    }

    public static WIFIScanner getInstance()
    {
        return wifiScanner;
    }

}
