# Code description
[TOC]

## BASIC-FUNCTION

### WIFI SCAN PART

#### STEP1 Firstly, u should get the permission in /app/manifests/AndroidManifest.xml

![](https://pad.degrowth.net/uploads/upload_e96c9205a574a231112f83c9b59e3444.png =500x)

>The code should be like this:
```XML=
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"></uses-permission>
<uses-permission android:name="android.permission.CHANGE_WIFI_STATE"></uses-permission>
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"></uses-permission>
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"></uses-permission>
```

---------------------------------

#### STEP2 Secondly, add permission through java code here:
![](https://pad.degrowth.net/uploads/upload_261b5e648a76c77e3e3d001279fc24f2.png =500x)
>The code part:
``` java=
private int location_request_code = 100; //This should be written outside of "onCreate" function

checkPermission(); // remember to write this inside of "onCreate"

//Remember to add this functions' implementation
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
```

-------------------------------

#### STEP3 Thirdly, turn on wifiManager in the same file as STEP2, and turn on broadcastReceiver, IntentFilter, then start scan:
>The code part:
```java=
WifiManager wifiManager;
Context context = getBaseContext();
wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
BroadcastReceiver wifiScanReceiver = new BroadcastReceiver() {
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

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        context.registerReceiver(wifiScanReceiver,intentFilter);
        boolean success = wifiManager.startScan();

```

--------------------------------------------

#### STEP4 Remember to add the scanSuccess function and scanFailed Function, these two are depend on your requirements
>My part is as below:
```java=
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
```

### POSITIONING PART

## EXTRA-PART
