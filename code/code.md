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

Position method: First we get the 2-d map, which has the x&y coordinate, then we turn it into the 1-d map, which only has the room number, and get the 4-d map, which has 4 strongest AP's signal level.
When we are going to find someone's position, we can get his/her 4 strongest APs, then we compare it with the point in the map.
We have two calculate methods: one is calculate the shortest Euclidean Distance, the other is calculate the biggest cosine.
The detailed information is in the video below:

{%bilibili BV1uz411v7NK %}

#### Build the class
|Class|Var1|Var2|Var3|Var4|Var5|
|-|-|-|-|-|-|
|AP|BSSID String|SSID String|level int|
|Point|id int|x,y int|ap1,ap2,ap3,ap4 AP|
|Map|pointList List<Point>|id String|

>This is the major code of Class AP, only include the two constructors
```java=
public class AP {
    private String BSSID;
    private String SSID;
    private int level;

    //constructor with out variable
    public AP()
    {
        BSSID = "00:00:00:00:00:00";
        SSID = "";
        level = 0;
    }
    //constructor with 3 vars
    public AP(String B,String S,int l)
    {
        BSSID = B;
        SSID = S;
        level = l;
    }
}
```

>This is the major code of Class Point only include two constructors
```java=
public class Point {
    private AP ap1,ap2,ap3,ap4; //4-dimension map's position
    private int id; //1-dimension map's position
    private int x,y; //2-dimension map's position

    //constructor without var
    public Point()
    {
        ap1 = new AP();
        ap2 = new AP();
        ap3 = new AP();
        ap4 = new AP();
        id = -1;  //-1 means not valued
        x = -1;
        y = -1;
    }
    //constructor with all vars
    public Point(AP ap1, AP ap2, AP ap3, AP ap4,int id,int x,int y)
    {
        this.ap1 = ap1;
        this.ap2 = ap2;
        this.ap3 = ap3;
        this.ap4 = ap4;
        this.id = id;
        this.x = x;
        this.y = y;
    }
}
```

>I won't show the map class for it is too simple.

#### Write the positioning algorithm
>Calculate by distance:
```java=
// calculate the Euclidean Distance
    private double calculateDis(Point p1, Point p2)
    {
        double distance = -1;


        distance = Math.sqrt((p1.getAp1().getLevel()-p2.getAp1().getLevel())*(p1.getAp1().getLevel()-p2.getAp1().getLevel())
                            +(p1.getAp2().getLevel()-p2.getAp2().getLevel())*(p1.getAp2().getLevel()-p2.getAp2().getLevel())
                            +(p1.getAp3().getLevel()-p2.getAp3().getLevel())*(p1.getAp3().getLevel()-p2.getAp3().getLevel())
                            +(p1.getAp4().getLevel()-p2.getAp4().getLevel())*(p1.getAp4().getLevel()-p2.getAp4().getLevel()));

        return distance;
    }

    private Point findTheNearestPointByDis()
    {
        Point nearestPoint = new Point();
        double minDis = MAX_DISTANCE;
        Iterator<Point> itr = currentMap.getPointList().iterator();
        while(itr.hasNext())
        {
            Point cP = itr.next();
            double dis = calculateDis(cP,currentPositionPoint);
            if (dis <= minDis)
            {
                minDis = dis;
                nearestPoint = cP;
            }
        }

        return nearestPoint;
    }
```

>Calculate by cosine:
```java=
// calculate the cosine in 4-dimensional map
    private double calculateCos(Point p1, Point p2)
    {
        double cosine = -1; // 180° is impossible

        Point Opoint = new Point();
        cosine = calculateInnerProduct(p1,p2) / (calculateDis(p1,Opoint)*calculateDis(p2,Opoint));

        return cosine;
    }

    // calculate the inner product
    private double calculateInnerProduct(Point p1, Point p2) {
        double InnerProduct = -2;

        InnerProduct = (p1.getAp1().getLevel()*p2.getAp1().getLevel()
                        +p1.getAp2().getLevel()*p2.getAp2().getLevel()
                        +p1.getAp3().getLevel()*p2.getAp3().getLevel()
                        +p1.getAp4().getLevel()*p2.getAp4().getLevel());

        return InnerProduct;
    }

    private Point findTheNearestPointByCos()
    {
        Point nearestPoint = new Point();
        double maxCos = 1;
        Iterator<Point> itr = currentMap.getPointList().iterator();
        while(itr.hasNext())
        {
            Point cP = itr.next();
            double dis = calculateCos(cP,currentPositionPoint);
            if (dis >= maxCos)
            {
                maxCos = dis;
                nearestPoint = cP;
            }
        }

        return nearestPoint;
    }
```

## EXTRA-PART
