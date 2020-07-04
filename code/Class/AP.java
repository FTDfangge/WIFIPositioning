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

    //GETTERS AND SETTERS FOR ALL VARS
    public void setBSSID(String BSSID) {
        this.BSSID = BSSID;
    }

    public String getBSSID() {
        return BSSID;
    }

    public String getSSID() {
        return SSID;
    }

    public void setSSID(String SSID) {
        this.SSID = SSID;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    //END OF GETTERS AND SETTERS
}
