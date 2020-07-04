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

    //GETTERS AND SETTERS
    public AP getAp1() {
        return ap1;
    }

    public void setAp1(AP ap1) {
        this.ap1 = ap1;
    }

    public AP getAp2() {
        return ap2;
    }

    public void setAp2(AP ap2) {
        this.ap2 = ap2;
    }

    public AP getAp3() {
        return ap3;
    }

    public void setAp3(AP ap3) {
        this.ap3 = ap3;
    }

    public AP getAp4() {
        return ap4;
    }

    public void setAp4(AP ap4) {
        this.ap4 = ap4;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    //END OF GETTERS AND SETTERS
}
