# Positioning
>This includes two apps, the wifi signal decetion app & positioning app(the main part)
- [ ]signal decetion
- [ ]positioning

## Signal detection
>Related classes for operating wifi are mainly placed under the android.net.wifi package. Use wifi related methods need to apply for some permissions:
- ACCESS_WIFI_STATE
- CHANGE_WIFI_STATE
- CHANGE_WIFI_MULTICAST_STATE

>Here u need to add this code to file "AndroidManifest.xml"

<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"></uses-permission>
<uses-permission android:name="android.permission.CHANGE_WIFI_STATE"></uses-permission>

-----------------
## Positioning
>
