import java.util.List;

public class Map {
    //singleton version
    private  Map map;
    private  List<Point> pointList;
    private String id;

    public Map() {

    }

    public void setPoints(List<Point> pList)
    {
        this.pointList = pList;
    }

    public void setID(String id)
    {
        this.id = id;
    }

    public List<Point> getPointList()
    {
        return pointList;
    }

    public String getID()
    {
        return id;
    }

}
